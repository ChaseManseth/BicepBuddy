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
    gender: {type: String},
    phoneNumber: {type: Number},
    profilePic: {type: String},
    //acceptedMatches: [{any: []}], //Once Match is Ready Add it
    //rejectedMatches: [{any: []}],
    //matches: [{any: []}],
    preferredGender: {type: String},
    weight: {type: Number},
    workoutStyle: {type: String},
    goals: {type: String},
    experience: {type: Number},
    timeOfDay: {type: Number},
    frequency: {type: Number}
});

module.exports = mongoose.model('User', userSchema);