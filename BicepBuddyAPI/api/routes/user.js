const express = require('express');
const router = express.Router();
const mongoose = require('mongoose');
const bcrypt = require('bcrypt');
const jwt = require('jsonwebtoken');

// Middleware
const checkAuth = require('../middleware/check-auth');
const isUser = require('../middleware/is-user');

const User = require('../models/user');

// Set this to an env variable
const JWT_KEY = "secret";
// END

// User signup
router.post('/signup', (req, res, next)=> {
    console.log(req.body);
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
                            lastname: req.body.lastname,
                            phoneNumber: req.body.phoneNumber,
                            age: req.body.age,
                            gender: req.body.gender,
                            preferredGender: req.body.preferredGender,
                            goals: req.body.goals,
                            frequency: req.body.frequency,
                            timeOfDay: req.body.timeOfDay,
                            workoutStyle: req.body.workoutStyle,
                            weight: req.body.weight,
                            experience: req.body.experience
                        });

                        user.save()
                            .then(result => {
                                const token = jwt.sign({
                                    email: user.email,
                                    userId: user._id
                                    }, 
                                    JWT_KEY,
                                    {
                                        expiresIn: "2h"
                                    });
                                res.status(201).json({
                                    message: 'User Created!',
                                    token: token,
                                    user: user
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
    User.find({email: req.body.email})
        .exec()
        .then(user => {
            if(user.length < 1) {
                return res.status(401).json({
                    message: 'Auth failed'
                });
            }
            bcrypt.compare(req.body.password, user[0].password, (err, result) => {
                if(err) {
                    return res.status(401).json({
                        message: 'Auth failed'
                    });
                }
                if(result) {
                    const token = jwt.sign({
                        email: user[0].email,
                        userId: user[0]._id
                        }, 
                        JWT_KEY,
                        {
                            expiresIn: "2h"
                        },
                    );
                    return res.status(200).json({
                        message: 'Auth successful',
                        token: token,
                        user: user
                    });
                }

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

// Update a user profile info
router.patch('/:userID', (req, res, next) => {
    const id = req.params.userID;
    const accessedId = req.headers.id;

    User.findByIdAndUpdate(id, req.body)
        .exec()
        .then(result => {
            res.status(200).json({
                message: 'User updated!'
            });
        })
        .catch(err => {
            console.log(err);
            res.status(500).json({
                error: err
            });
        });

    // if(accessedId != null && id == accessedId) {
        
    // } else {
    //     res.status(403).json({
    //         message: 'Action not allowed!'
    //     });
    // }
});

// Get a list of all the users
router.get('/', (req, res, next) => {
    User.find()
        // .select('_id firstname lastname email')
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

            res.status(200).json(docs);
        })
        .catch(err => {
            console.log(err);
            res.status(500).json({
                error: err
            });
        });
});

// Get a list of users based on Gender
router.get('/get_users_by_gender/:gender', (req, res, next) => {
    const gender = req.params.gender;

    // Male
    if(gender == 'male') {
        User.find({'gender': 'Male'})
        .exec()
        .then(docs => {
            res.status(200).json(docs);
        })
        .catch(err => {
            console.log(err);
            res.status(500).json({
                error: err
            });
        });
    } 
    
    // Female
    else if (gender == 'female') {
        User.find({'gender': 'Female'})
        .exec()
        .then(docs => {
            res.status(200).json(docs);
        })
        .catch(err => {
            console.log(err);
            res.status(500).json({
                error: err
            });
        });
    }

    // No Gender Specified
    else {
        User.find({'gender': 'N/A'})
        .exec()
        .then(docs => {
            res.status(200).json(docs);
        })
        .catch(err => {
            console.log(err);
            res.status(500).json({
                error: err
            });
        });
    }
});

// Get a specific user
router.get('/:userID', (req, res, next) => {
    const id = req.params.userID;
    const accessedId = req.headers.id;
    // console.log(accessedId);
    // console.log(id);

    User.findById(id)
    .exec()
    .then(result => {
        return res.status(200).json({
            result: result
        });
    })
    .catch(err => {
        return res.status(409);
    });
     // Check If User accessing this user is self
    // if(accessedId != null && id == accessedId) {
    //     User.findById(id)
    //     .exec()
    //     .then(result => {
    //         return res.status(200).json({
    //             result: result
    //         });
    //     })
    //     .catch(err => {
    //         return res.status(409);
    //     });
    // } else {
    //     User.findById(id)
    //     .select('firstname lastname gender profilePic preferredGender' + 
    //     'weight workoutStyle goals experience timeOfDay frequency')
    //     .exec()
    //     .then(result => {
    //         return res.status(200).json({
    //             result: result
    //         });
    //     })
    //     .catch(err => {
    //         return res.status(409);
    //     });
    // }
    
});

// Delete User
// TODO: Make sure Admins can delete others accounts
router.delete("/:userID", (req, res, next) => {
    const id = req.params.userID;
    // const accessedId = req.headers.id;
    // if(accessedId != null && id == accessedId) {
       
    // } else {
    //     res.status(403).json({
    //         message: 'Access not allowed!'
    //     });
    // }
    User.deleteOne({_id: id})
    .exec()
    .then(result => {
        res.status(200).json({
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

// Add an match id to a users acceptedMatch array
router.patch('/:userID/accept/:matchID', (req, res, next) => {
    const id = req.params.userID;
    const matchId = req.params.matchID;

    User.findByIdAndUpdate(id, {$push: {acceptedMatches: matchId}})
        .exec()
        .then(result => {
            res.status(200).json({
                message: 'Match added!'
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