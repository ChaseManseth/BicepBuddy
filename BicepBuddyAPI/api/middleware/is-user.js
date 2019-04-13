const jwt = require('jsonwebtoken');

module.exports = (req, res, next) => {
    // Replace the key or string "secret"
        const id = req.headers.id;
        if(req.params.userId == id) {
            next();
        } else {
            return res.status(401).json({
                message: "Can't delete another User!"
            });
        }
}