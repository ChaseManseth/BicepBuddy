
const mysql = require('mysql');
const express = require('express');
const app = express();

const port = process.env.PORT || 3000;

app.get('/', function(req, res) {
    res.send("Hello Worl123123d!");
});

// var con = mysql.createConnection({
//   host: "localhost",
//   user: "node",
//   password: "chase"
// });
//
// con.connect(function(err) {
//   if (err) throw err;
//   console.log("Connected!");
// });

app.listen(port, () => console.log(`Listening on port ${port}....`));
