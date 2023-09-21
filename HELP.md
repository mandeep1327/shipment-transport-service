# Getting Started
Spring/Java microservice
Design a Shipment Loading System that efficiently assigns shipments to available transport vehicles based on their weight and the remaining capacity in the vehicles. The system should optimize the loading process by giving precedence to vehicle with lowest capacity/available capacity that can accommodate the shipment weight.


### Prerequisites

* Java SDK 11
* Apache-maven-3.5.0
* run the docker-compose file and make postgres up and running

## Dependencies
* Postgres Database

#### Build application:
Build: `mvn clean install`

#### Starting application:
* To start server locally for manual testing/debugging: `mvn spring-boot:run`

Run Application Build using docker.
docker build . 
docker run

API end-point:
1) Create Shipment -POST
http://localhost:8080/shipment
Request:
{
"source":"delhi",
"destination":"bangalore",
"weight":"20",
"dimension":{
"length":"2",
"weight":"5",
"height":"4"
}
}
2) Create Transport -POST
   http://localhost:8080/transport
Request:
{
   "capacity": "20",
   "currentLoad": "0",
   "dimension": {
   "length": "2",
   "weight": "5",
   "height": "4"
   }
   }
3) Load Assignment -POST
   http://localhost:8080/load-assignment
NO Body.

4) Retrieve Loaded - Retrive All shipments with assigned vehicle.-GET
   http://localhost:8080/getshipment

postman collection can be found here:
shipment-loading-service\src\main\java\com\microservices\shipment\service\postman

