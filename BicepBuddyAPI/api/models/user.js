// Author: Chase Manseth
// Date: 4/30/2019

// User Object Model
const mongoose = require('mongoose');

const userSchema = mongoose.Schema({
    _id: mongoose.Schema.Types.ObjectId,
    email: {
        type: String, 
        required: true, 
        unique: true, 
        match: /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/
    },
    password: {type: String, required: true},
    firstname: {type: String, required: true},
    lastname: {type: String, required: true},
    dateCreated: {type: Date, default: Date.now},
    age: {type: String, default: "none"},
    gender: {type: String, default: "none"},
    phoneNumber: {type: String, default: "none"},
    profilePic: {type: String, default: "none"},
    acceptedMatches: [{
        type: String
    }],
    rejectedMatches: [{
        type: String
    }],
    idleMatches: [{
        type: String
    }],
    waitingMatches: [{
        type: String
    }],
    preferredGender: {type: String, default: "none"},
    weight: {type: String, default: "none"},
    workoutStyle: {type: String, default: "none"},
    goals: {type: String, default: "none"},
    experience: {type: String, default: "none"},
    timeOfDay: {type: String, default: "none"},
    frequency: {type: String, default: "none"}
});

module.exports = mongoose.model('User', userSchema);