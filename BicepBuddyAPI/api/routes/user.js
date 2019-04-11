const express = require('express');
const router = express.Router();
const mongoose = require('mongoose');
const bcrypt = require('bcrypt');
const jwt = require('jsonwebtoken');
const checkAuth = require('../middleware/check-auth');

const User = require('../models/user');

// Set this to an env variable
const JWT_KEY = "secret";
// END

// User signup
router.post('/signup', (req, res, next)=> {
    // See if account exists
    User.find({email: req.body.email})
        .exec()
        .then(user => {
            if(user.length >= 1) {
                return res.status(409).json({
                    message: 'Email already exists'
                });
            } else {
                // If not encrypt their password and create a new user
                bcrypt.hash(req.body.password, 10, (err, hash) => {
                    if(err) {
                        return res.status(500).json({
                            error: err
                        });
                    } else {
                        const user = new User({
                            _id: new mongoose.Types.ObjectId(),
                            email: req.body.email,
                            password: hash,
                            firstname: req.body.firstname,
                            lastname: req.body.lastname
                        });

                        user.save()
                            .then(result => {
                                res.status(201).json({
                                    message: 'User Created!'
                                });
                            })
                            .catch(err => {
                                console.log(err);
                                res.status(500).json({
                                    error: err
                                });
                            })
                    }
                })
            }
        })
});

// Login a user
router.post('/login', (req, res, next) => {
    User.findOne({email: req.body.email})
        .exec()
        .then(user => {
            // Make sure user can be found
            if(user != null) {
                return res.status(401).json({
                    message: 'Auth failed!'
                });
            }

            // Check if password is valid
            bcrypt.compare(req.body.password, user.password, (err, result) => {
                if(err) {
                    return res.status(401).json({
                        message: 'Auth failed'
                    });
                }

                if(result) {
                    const token = jwt.sign({
                        email: user.email,
                        userId: user._id
                    },
                    JWT_KEY,
                    {
                        expiresIn: '1h'
                    });

                    return res.status(200).json({
                        message: 'Auth successful',
                        token: token
                    });
                }

                // Error if login doesn't work
                return res.status(401).json({
                    message: 'Auth failed'
                });
            });
        })
        .catch(err => {
            console.log(err);
            res.status(500).json({
                error: err
            });
        });
});

// Get a list of all the users
router.get('/', (req, res, next) => {
    User.find()
        .select('_id firstname lastname email')
        .exec()
        .then(docs => {
            const response = {
                count: docs.length,
                users: docs.map(doc => {
                    return {
                        firstname: doc.firstname,
                        lastname: doc.lastname,
                        _id: doc._id,
                        email: doc.email
                    }
                })
            };

            res.status(200).json(response);
        })
        .catch(err => {
            console.log(err);
            res.status(500).json({
                error: err
            });
        });
});

// Delete User
// TODO: Make sure that a user can only delete their account
router.delete("/:userId", checkAuth, (req, res, next) => {
    User.remove({_id: req.params.userId})
        .exec()
        .then(result => {
            res.status(500).json({
                messsage: 'User Deleted'
            });
        })
        .catch(err => {
            console.log(err);
            res.status(500).json({
                error: err
            });
        });
});


module.exports = router;