// Author: Chase Manseth
// Date: 4/30/2019

// App Logic
const express = require('express');
const app = express();
const morgan = require('morgan');
const bodyParser = require('body-parser');
const mongoose = require('mongoose');

const userRoutes = require('./api/routes/user');
const matchRoutes = require('./api/routes/match');
const chatRoutes = require('./api/routes/chat');

// Delete this when in production
const mongodbPW = "uXwozBcr3tFtWnbI";
// Delete this when in production

mongoose.connect('mongodb+srv://Manseth:' + mongodbPW + '@bicepbuddy-gosub.mongodb.net/test?retryWrites=true&authSource=admin',
    {
        useNewUrlParser: true
    }
);

mongoose.set('useFindAndModify', false);
mongoose.set('useCreateIndex', true);

mongoose.Promise = global.Promise;

app.use(morgan('dev'));
app.use('/uploads', express.static('uploads'));
app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());

// Headers
app.use((req, res, next) => {
    res.header('Access-Control-Allow-Origin', '*');
    res.header('Access-Control-Allow-Headers', 'Origin, X-Requested-With, Content-Type, Accept, Authorization');

    if (req.method === 'OPTIONS') {
        res.header('Access-Control-Allow-Methods', 'PUT, POST, PATCH, DELETE, GET');
        return res.status(200).json({});
    }

    next();
});

// Routes which should handle requests
app.use('/user', userRoutes);
app.use('/match', matchRoutes);
app.use('/chat', chatRoutes);

// Error Handling
app.use((req, res, next) => {
    const error = new Error('Not found!');
    error.status = 404;
    next(error);
});

app.use((error, req, res, next) => {
    res.status(error.status || 500);
    res.json({
        error: {
            message: error.message
        }
    });
});

module.exports = app;