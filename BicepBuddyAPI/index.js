// Author: Chase Manseth
// Date: 4/30/2019

// Server Start
const http = require('http');
const app = require('./app');

const port = process.env.PORT || 3000;

const server = http.createServer(app);

console.log('Server is now running');

server.listen(port);