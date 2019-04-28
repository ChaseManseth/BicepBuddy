const mongoose = require('mongoose');

const chatSchema = mongoose.Schema({
    _id: mongoose.Schema.Types.ObjectId,
    userId: {type: String, required: true},
    otherUserId: {type: String, required: true},
    dateCreated: {type: Number, default: Date.now},
    message: {type: String, required: true}
});

module.exports = mongoose.model('Chat', chatSchema);