# HotelRepository
Hotel Repository to Insert Hotel Information Thread Safe and Search Hotel based on multiple fields for high scalibilty and low latency use case.

Code is written in Java with Spring Framework.

Persistence Storage Used are Mysql and Elastic Search , which are embedded one for this project to avoid external dependency.

### To Start the service follow the step : 
1. Git Clone <n>
$ git clone  https://github.com/guptaanamika/HotelRepository.git .
2. Build classes and run Junit .
$ cd HotelRepository
$ mvn clean install
3. Run Service .
$ java -jar HotelRepositoryService/target/HotelRepositoryService.jar


Now, your service has started on http://localhost:8080.
Use HotelRepositoryTest , in Client to play around with service API.

To locally explore more use cases , you can explore Junits in HotelRepositoryService.

More on architect can be found in doc
https://docs.google.com/document/d/1qhMQEu5GCdHPsBRwZXo2uEhYcaLeDwJSxABpIp-OD2o/edit?usp=sharing
