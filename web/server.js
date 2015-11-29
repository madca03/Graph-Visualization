var http = require('http');

// Port number
const PORT = 3000;

// function to handle requests and send respons
function handleRequest(request, response) {
  response.end('It works! Path Hit: ' + request.url);
}

// create server
var server = http.createServer(handleRequest);

// start the server
server.listen(PORT, function() {
  console.log("Server listening on: http://localhost:%s", PORT);
})