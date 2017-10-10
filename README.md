## Service
Service that navigates a imaginary robotic hoover (much like a Roomba) through an equally imaginary room.

It takes the a room dimensions, the locations of the dirt patches, the hoover location and the driving instructions as input.

Runs a algorithm to calculate the cleaned patches and the robot final position, and return it as output.

## Input

Program input will be received in a json payload with the format described here.

Example:

```javascript
{
  "roomSize" : [5, 5],
  "coords" : [1, 2],
  "patches" : [
    [1, 0],
    [2, 2],
    [2, 3]
  ],
  "instructions" : "NNESEESWNWW"
}
```

## Output

Service output should be returned as a json payload.

Example (matching the input above):

```javascript
{
  "coords" : [1, 3],
  "patches" : 1
}
```
Where `coords` are the final coordinates of the hoover and `patches` is the number of cleaned patches.
Moreover, the services persists every input and output to a database.


## Requirments
- GIT
- JDK 8
- Maven 3
- Curl or similar to do the requests to the application

## Instructions
Checkout the project:
````
  git clone https://github.com/emidioacd/voting.git

````

Compile and start the project:
````
mvn clean package & java -jar target/Hoover-1.0.0.jar

````

Test it:
````
curl -X POST --header 'Content-Type: application/json' --header 'Accept: application/json' -d '{"roomSize" : [5, 5], "coords" : [1, 2], "patches" : [[1, 0],[2, 2],[2, 3]], "instructions" : "NNESEESWNWW"}' 'http://localhost:8080/hoover'

````