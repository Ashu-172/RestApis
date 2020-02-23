# RestApis
=======================================
=======================================

BasicApi01:

URL: localhost:4567/hello

Query Parameter: NA

Request Parameter/Body: NA

Description: When we make a request to the url, this API will print "Hello World..!" as response. this API will neither take any request paramet nor any query parameter.

=======================================

BasicApi02:

URL: localhost:4567/hello/:name

Query Parameter: name   Value:Ashutosh

Request Parameter: NA

Description: When we make a request to the url, with the query parameter, this API will print "Hello Ashutosh..! How are you doing..?". this api will take value of the query parameter and print that in the response.

=======================================

BasicApi03:

URL 1: localhost:4567/hello 

URL 2: localhost:4567/hello/:name

Query Parameter: name   Value:Ashutosh

Request Parameter: NA

Response:

1. For URL 1

{

"id": 1006,

"name": "User",

"greeting": "Hello World..!"

}

2.ForURL 2

{

"id": 1007,

"name": "Ashutosh",

"greeting": "Hello Ashutosh..!"

}

Description:

when request will be made to the URL then it will give a JSON response which will have three parameters.

a. "id": it will give an id of the response which will increase as the number of requests will get increased.

b. "name": it will be the name of the user, in case of requesting to URL1, its value will be "user". In case of the requesting URL2, its value will be the value of :name which is the query parameter.

c. "greeting":it will the greeting for the user, in case of URL1, its value will be "Hello World..!". In case of the requesting URL2. its value will be "Hello :name" where :name will be the value of the query Parameter.

=======================================

