// Author: Chase Manseth
// Date: 4/30/2019

// Match Routes
const express = require('express');
const router = express.Router();
const mongoose = require('mongoose');
const bcrypt = require('bcrypt');
const jwt = require('jsonwebtoken');

// Middleware
const checkAuth = require('../middleware/check-auth');

const Match = require('../models/match');

// Set this to an env variable
const JWT_KEY = "secret";
// END

// Match Creation
router.post('/', (req, res, next) => {
    const match = new Match({
        _id: new mongoose.Types.ObjectId(),
        userId: req.body.userId,
        matchedUserId: req.body.matchedUserId,
        status: req.body.status,
        strength: req.body.strength
    });

    match.save()
        .then(result => {
            console.log('Match Created!');
            res.status(201).json({
                message: 'Match Created!',
                match: result
            });
        })
        .catch(err => {
            console.log(err);
            res.status(500).json({
                error: err
            });
        });
});

// Get Matches
router.get('/', (req, res, next) => {
    Match.find()
        .exec()
        .then(docs => {
            res.status(200).json({
                count: docs.length,
                matches: docs
            });
        })
        .catch(err => {
            console.log(err);
            res.status(500).json({
                err: error
            });
        });
});

// Get Match by Id
router.get('/:matchId', (req, res, next) => {
    const id = req.params.matchId;
    Match.findById(id)
        .exec()
        .then(result => {
            res.status(200).json({
                match: result
            });
        })
        .catch(err => {
            console.log(err);
            res.status(500).json({
                error: err
            });
        });
});

// Update a Match
router.patch('/:matchId', (req, res, next) => {
    const id = req.params.matchId;
    Match.findByIdAndUpdate(id, req.body)
        .exec()
        .then(result => {
            res.status(200).json({
                message: 'Match Updated!'
            });
        })
        .catch(err => {
            console.log(err);
            res.status(500).json({
                error: err
            });
        })
});

// Delete Match by Id
router.delete('/:matchId', (req, res, next) => {
    const matchId = req.params.matchId;

    Match.findByIdAndDelete(matchId)
        .exec()
        .then(result => {
            console.log('Match deleted');
            res.status(200).json({
                message: 'Match deleted'
            });
        })
        .catch(err => {
            console.log(err);
            res.status(500).json({
                error: err
            });
        });
})



module.exports = router;