#Smart Campus API – Room & Sensor Management System

#Student Information
#Name: Danidu Walisinha
#UoW ID: 
#IIT ID: 20231331

#1. API Design Overview

This project implements a RESTful API for a Smart Campus environment using JAX-RS (Jersey). The system models how physical rooms and IoT sensors interact within a university campus.

The API is structured around three core resources:

#Rooms
Represents physical locations such as lecture halls and labs. Each room contains metadata and a list of associated sensors.

#Sensors
Represents IoT devices (e.g., temperature, CO2, occupancy sensors) that are assigned to rooms.

#Sensor Readings
Represents time-based data collected from sensors. Each sensor maintains a history of readings.

#Key Design Decisions

1.Resource-based structure:
Each entity (Room, Sensor, Reading) is treated as a REST resource.

2.Nested resources:
Sensor readings are accessed using:
/api/v1/sensors/{sensorId}/readings

3.In-memory storage:
The system uses HashMap and ArrayList instead of a database, as required.

4.Error handling:
Custom exceptions and exception mappers are used to return structured JSON responses.

5.Logging:
Request and response filters are used to log API activity centrally.

#2. Step-by-step instructions on how to build the project and launch the server
 
Step 01. Clone the repository
git clone https://github.com/YOUR_USERNAME/SmartCampusAPI.git

Step 02. Navigate into the project directory
cd SmartCampusAPI

Step 03. Compile the project
mvn clean compile

Step 04. Run the application
mvn exec:java

Step 05. Access the API
Open your browser or Postman and go to:
http://localhost:8080/api/v1

#3. Sample curl Commands
Below are example requests that demonstrate how the API works.

1. Check API Root (Discovery Endpoint)
curl -X GET http://localhost:8080/api/v1

2. Create a New Room
curl -X POST http://localhost:8080/api/v1/rooms \
-H "Content-Type: application/json" \
-d '{"id":"LAB-01","name":"Computer Lab","capacity":60}'

3. Retrieve All Rooms
curl -X GET http://localhost:8080/api/v1/rooms

4. Register a Sensor for a Room
curl -X POST http://localhost:8080/api/v1/sensors \
-H "Content-Type: application/json" \
-d '{"id":"TEMP-01","type":"Temperature","status":"ACTIVE","currentValue":0,"roomId":"LAB-01"}'

5. Filter Sensors by Type
curl -X GET "http://localhost:8080/api/v1/sensors?type=Temperature"

6. Add a Reading to a Sensor
curl -X POST http://localhost:8080/api/v1/sensors/TEMP-01/readings \
-H "Content-Type: application/json" \
-d '{"value":23.8}'

7. Get Sensor Readings
curl -X GET http://localhost:8080/api/v1/sensors/TEMP-01/readings

8. Attempt to Delete a Room with Sensors (Error Case)
curl -X DELETE http://localhost:8080/api/v1/rooms/LAB-01

#4. Error Handling Approach

The API uses structured JSON responses for all error scenarios.
Instead of returning plain text, responses follow a consistent format:

{
  "status": 404,
  "error": "Not Found",
  "message": "Room not found"
}

#Implemented Scenarios
409 Conflict – deleting a room that still contains sensors
422 Unprocessable Entity – sensor linked to a non-existing room
403 Forbidden – adding readings to a sensor in maintenance mode
500 Internal Server Error – unexpected failures
5. Logging Strategy

#5. Logging Strategy

A custom logging filter is used to track:
incoming request method and URL
outgoing response status codes

This ensures:
better debugging
cleaner resource classes
centralized logging logic

#6. Summary

This API demonstrates a complete RESTful backend using JAX-RS, including:

proper resource structuring
nested endpoints
validation and exception handling
logging and error management

The system simulates a real-world smart campus scenario and follows best practices for REST API design.