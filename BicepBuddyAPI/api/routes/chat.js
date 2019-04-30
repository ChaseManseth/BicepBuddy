// Author: Chase Manseth
// Date: 4/30/2019

// Chat Routes
const express = require('express');
const router = express.Router();
const mongoose = require('mongoose');
const bcrypt = require('bcrypt');
const jwt = require('jsonwebtoken');

// Middleware
const checkAuth = require('../middleware/check-auth');

const Chat = require('../models/chat');

// Set this to an env variable
const JWT_KEY = "secret";
// END

// Chat Message Creation
router.post('/', (req, res, next) => {
    const chat = new Chat({
        _id: new mongoose.Types.ObjectId(),
        userId: req.body.userId,
        otherUserId: req.body.otherUserId,
        message: req.body.message
    });

    chat.save()
        .then(result => {
            console.log('Chat Created!');
            res.status(201).json({
                message: 'Chat Created!',
                chat: result
            });
        })
        .catch(err => {
            console.log(err);
            res.status(500).json({
                error: err
            });
        });
});

// Get all Chats
router.get('/', (req, res, next) => {
    Chat.find()
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

// Get Chat by Id
router.get('/:chatId', (req, res, next) => {
    const id = req.params.chatId;
    Chat.findById(id)
        .exec()
        .then(result => {
            res.status(200).json({
                chat: result
            });
        })
        .catch(err => {
            console.log(err);
            res.status(500).json({
                error: err
            });
        });
});

// Get chats from user to other user
router.get('/from/:userId/to/:otherUserId', (req, res, next) => {
    const userId = req.params.userId;
    const otherUserId = req.params.otherUserId;

    var query = {
        userId: userId,
        otherUserId: otherUserId
    };
    Chat.find(query)
        .exec()
        .then(result => {
            console.log('Chat Made');
            res.status(200).json(result);
        })
        .catch(err => {
            console.log(err);
            res.status(500).json({
                error: err
            });
        });
});

// Delete Chat msg
router.delete('/:chatId', (req, res, next) => {
    const chatId = req.params.chatId;

    Chat.findByIdAndDelete(chatId)
        .exec()
        .then(result => {
            console.log('Chat deleted');
            res.status(200).json({
                message: 'Chat deleted'
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