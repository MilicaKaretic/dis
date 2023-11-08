DISTRIBUTED INFORMATION SYSTEMS

Application for Online Treatment Reservation - description of business logic
This application implements a microservice system for online treatment reservations. The microservice system (implemented using Spring Boot and Spring Cloud) consists of the following services:

Treatment Service - This microservice has the role of a catalog and it is possible to create new treatments and view all treatments from the catalog.
Reservation Service - This microservice is for making treatment reservations.
Treatment Availability Service - After creating treatment reservation, this microservice checks whether the treatment is available or not.
Notification Service - This microservice is used to send notifications, after reservation is made.
Discovery Service - Enables dynamic discovery and communication between services.
API Gateway - Entry point for all client-side requests, enabling access control, request routing, and data authentication from various microservices.
