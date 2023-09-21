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

Create Shipment curl --location --request POST 'http://localhost:8080/shipment'
--header 'Content-Type: application/json'
--data-raw '{ "source": "delhi", "destination": "bangalore", "weight": "24", "dimension": { "length": "2", "weight": "5", "height": "4" } }'
Create Transport curl --location --request POST 'http://localhost:8080/transport'
--header 'Content-Type: application/json'
--data-raw '{ "capacity": "40.5", "currentLoad": "10", "dimension": { "length": "2", "weight": "5", "height": "4" } }'
Load Assignment curl --location --request POST 'http://localhost:8080/load-assignment'
--header 'Content-Type: application/json'
--data-raw ' { "source":"delhi", "destination":"bangalore", "weight":"20", "dimension":{ "length":"2", "weight":"5", "height":"4"
}

} '

Retrieve Loaded curl --location --request GET 'http://localhost:8080/getshipment' '

postman collection can be found here:
shipment-loading-service\src\main\java\com\microservices\shipment\service\postman


