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
    lastname: {type: String, required:true},
    dateCreated: {type: Date, default: Date.now},
    age: {type: Number},
    gender: {type: String, default: "none"},
    phoneNumber: {type: Number},
    profilePic: {type: String, default: "none"},
    //acceptedMatches: [{any: []}], //Once Match is Ready Add it
    //rejectedMatches: [{any: []}],
    //matches: [{any: []}],
    preferredGender: {type: String, default: "none"},
    weight: {type: Number, default: 0},
    workoutStyle: {type: String, default: "none"},
    goals: {type: String, default: "none"},
    experience: {type: Number, default: 0},
    timeOfDay: {type: Number, default: 0},
    frequency: {type: Number, default: 0}
});

module.exports = mongoose.model('User', userSchema);