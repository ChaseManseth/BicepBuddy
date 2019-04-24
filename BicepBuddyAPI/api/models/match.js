const mongoose = require('mongoose');

const matchSchema = mongoose.Schema({
    _id: mongoose.Schema.Types.ObjectId,
    userId: {type: String, required: true},
    matchedUserId: {type: String, required: true},
    status: {type: Number, default: 2},
    dateCreated: {type: Date, default: Date.now},
    killDate: {type: Date, default: Date.now},
    strength: {type: Number, default: 0}
});

module.exports = mongoose.model('Match', matchSchema);