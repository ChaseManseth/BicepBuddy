
const mysql = require('mysql');
const express = require('express');
const app = express();

app.get('/', function(req, res) {
    res.send("Hello World!");
});

var con = mysql.createConnection({
  host: "localhost",
  user: "node",
  password: "chase"
});

con.connect(function(err) {
  if (err) throw err;
  console.log("Connected!");
});

app.listen(3000, () => console.log('Listening on port 3000....'));
