## DISTRIBUTED INFORMATION SYSTEMS

## Application for Online Treatment Reservation - description of business logic
This application implements a microservice system for online treatment reservations. The microservice system (implemented using `Spring Boot` and `Spring Cloud`) consists of the following services:

- Treatment Service - This microservice has the role of a catalog and it is possible to create new treatments and view all treatments from the catalog.
- Reservation Service - This microservice is for making treatment reservations.
- Treatment Availability Service - After creating treatment reservation, this microservice checks whether the treatment is available or not.
- Notification Service - This microservice is used to send notifications, after reservation is made.
- Discovery Service - Enables dynamic discovery and communication between services.
- API Gateway - Entry point for all client-side requests, enabling access control, request routing, and data authentication from various microservices.

## Online Treatment Reservation - Microservice system diagram


The Treatment Service is talking to a `MongoDB` database, the Reservation service and the Treatment Availability service are talking to the `MySQL` database, to store all the information about availability and reservations. Notification service is responsible for sending notifications to the users and that's the reason why it's `stateless service` which doesn't have any database to communicate with.

Also, synchronous and asynchronous communication between services has been established. [Kafka](https://spring.io/projects/spring-kafka) was used for implementing asynchronous communication. Services are secured using the authorization server called as [Keycloak](https://www.keycloak.org/getting-started/getting-started-docker). We need to choose `OAuth 2.0` type of authorization and to generate `Access Token` before sending request to our application.

### Discovery Service

The Discovery Server ([Eureka Server](https://spring.io/projects/spring-cloud-netflix)), is a core component in a microservices architecture. It is a key component that helps manage and facilitate the dynamic discovery of microservices within the system. It plays a crucial role in enabling the communication and coordination of various microservices in a decentralized and scalable manner. Some key aspects of a discovery server:

- Service Registration: Microservices register themselves with the discovery server upon startup. This registration typically includes information about the service, such as its name, version, network location (IP address and port), and any metadata that might be useful for service discovery.
- Service Discovery: Other microservices can query the discovery server to find information about the available services. They can search for services based on criteria like service name, version, or metadata. The discovery server provides information on how to contact the desired service.
- Load Balancing: In addition to providing service discovery, some discovery servers offer load balancing capabilities. They distribute incoming requests among multiple instances of a service to ensure even distribution of traffic and improve fault tolerance.
- Health Checking: Discovery servers often monitor the health of registered services. They periodically ping services to check their availability and health status. Unhealthy services can be removed from the registry, ensuring that clients only interact with healthy instances.
- Dynamic Updates: Microservices can join or leave the system dynamically. When a new service instance is deployed or an existing one goes offline, the discovery server is responsible for updating its registry accordingly.
- Redundancy and Failover: Discovery servers themselves can be designed to be highly available and fault-tolerant to prevent them from becoming single points of failure in the microservices ecosystem.
- Integration with Orchestration and Proxy Layers: Discovery servers are often used in conjunction with other components, such as API gateways, reverse proxies, and orchestration tools (e.g., Kubernetes) to provide a comprehensive solution for service communication and routing.

Discovery server simplifies the management of microservices and helps ensure that they can find and communicate with each other in a dynamic and scalable environment, which is a fundamental requirement in a microservices-based system

### API Gateway

[API Gateway](https://spring.io/projects/spring-cloud-gateway) is a central component in a microservices architecture that acts as a reverse proxy for handling and managing requests to the various microservices within the system. It serves as an entry point for clients, such as web or mobile applications, to access the functionalities provided by the underlying microservices. The API gateway routes incoming requests to the appropriate microservice based on the request's URL, HTTP method, and other criteria. It acts as a traffic cop, directing client requests to the appropriate backend services. It provides functionalities such as authentication, authorization, load balancing, and request transformation (data format conversion, payload size reduction, and response filtering). An API gateway simplifies the client-side experience, making it easier for applications to interact with a complex network of microservices.

### Distributed Tracing

Microservices are set up to send trace data to the `Zipkin` server asynchronously via message brokers. Distributed Tracing is the process of tracking and analyzing the path of a request as it flows through a system of microservices. Zipkin collects timing data from each service involved in handling a request, and then aggregates this data into a single comprehensive trace

### Circuit Breaker

[Circuit Breaker](https://spring.io/projects/spring-cloud-circuitbreaker) is a design pattern to create resilient microservices by limiting the impact of service failures and latencies. The major aim of the Circuit Breaker pattern is to prevent any cascading failure in the system. In a microservice system, failing fast is critical.
If there are failures in the Microservice ecosystem, then you need to fail fast by opening the circuit. This ensures that no additional calls are made to the failing service so that we return an exception immediately. This pattern also monitors the system for failures and, once things are back to normal, the circuit is closed to allow normal functionality.In this application we are using the `Resilience4j` library for implementing a Circuit Breaker pattern.

### Prometheus and Grafana

Prometheus and Grafana are commonly used for `monitoring` and `observability` in distributed systems. Prometheus is used for collecting and storing time-series data, while Grafana is used like for visualizing and analyzing this data. By implementing tools like Prometheus and Grafana, you can detect issues and optimize performance, providing exceptional user experience. 

