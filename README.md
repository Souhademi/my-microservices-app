Microservices Company Management System

A Spring Boot Microservices application for managing companies, jobs, and reviews.
The system uses Spring Cloud components for service discovery, centralized configuration, and distributed tracing.

🏗️ Architecture

The application is composed of the following services:

CompanyMS — Manages companies.
JobMS — Manages job postings.
ReviewMS — Manages company reviews.
Config Server — Provides centralized configuration for all microservices.
Eureka Server — Service discovery and registration.
Zipkin — Distributed tracing and request monitoring.
PostgreSQL — Database used by the microservices.
Architecture Overview
Client
  │
  ▼
JobMS
  │
  │ discovers CompanyMS through Eureka
  ▼
Eureka Server
  │
  ▼
CompanyMS
  │
  ▼
Company PostgreSQL
🛠️ Technologies
Technology	Purpose
Java	Programming language
Spring Boot	Microservice development
Spring Cloud	Microservices infrastructure
Spring Data JPA	Database access
PostgreSQL	Relational database
Spring Cloud Config	Centralized configuration
Eureka	Service discovery
Zipkin	Distributed tracing
Maven	Dependency management
REST API	Communication between services
Git / GitHub	Version control
📁 Project Structure
company-microservices/
│
├── configserver/
│   └── src/
│
├── eurekaserver/
│   └── src/
│
├── companyms/
│   └── src/
│
├── jobms/
│   └── src/
│
├── reviewms/
│   └── src/
│
├── config/
│   ├── companyms.yml
│   ├── jobms.yml
│   └── reviewms.yml
│
├── docker-compose.yml
│
└── README.md
🔗 Microservices
1. CompanyMS

Responsible for company management.

Main operations:

POST   /companies
GET    /companies
GET    /companies/{id}
PUT    /companies/{id}
DELETE /companies/{id}

Example company:

{
  "name": "Tech Solutions",
  "description": "Software development company",
  "city": "Constantine",
  "country": "Algeria"
}
2. JobMS

Responsible for managing job offers.

Main operations:

POST   /jobs
GET    /jobs
GET    /jobs/{id}
PUT    /jobs/{id}
DELETE /jobs/{id}

Example job:

{
  "title": "Java Backend Developer",
  "description": "Develop Spring Boot microservices",
  "minSalary": 1000,
  "maxSalary": 2000,
  "location": "Remote",
  "companyId": 1
}

JobMS communicates with CompanyMS to retrieve company information.

3. ReviewMS

Responsible for managing company reviews.

Main operations:

POST   /reviews
GET    /reviews
GET    /reviews/{id}
PUT    /reviews/{id}
DELETE /reviews/{id}

Example review:

{
  "title": "Great company",
  "description": "Good environment for developers",
  "rating": 5,
  "companyId": 1
}

ReviewMS communicates with CompanyMS to retrieve company information.

☁️ Config Server

The Config Server provides centralized configuration to all microservices.

Example:

spring:
  application:
    name: companyms

  config:
    import: optional:configserver:http://localhost:8071

Centralized configuration can contain:

Database configuration
Eureka configuration
Zipkin configuration
Application properties
Service URLs

Example configuration:

server:
  port: 8081

spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/companydb
    username: postgres
    password: postgres

eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka
🔎 Eureka Server

Eureka provides service discovery.

Eureka Dashboard:

http://localhost:8761

Each microservice registers itself with Eureka.

Example:

eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka

Registered services:

COMPANYMS
JOBMS
REVIEWMS
CONFIGSERVER

This allows services to communicate using service names instead of hard-coded IP addresses.

Example:

http://COMPANYMS/companies/1
📊 Zipkin

Zipkin is used for distributed tracing.

It allows you to follow a request across multiple microservices.

Example:

Client
  │
  ▼
JobMS
  │
  ├──────► CompanyMS
  │
  └──────► ReviewMS

Zipkin helps identify:

Request latency
Service-to-service calls
Errors
Slow services
Distributed request flow

Zipkin UI:

http://localhost:9411
🗄️ PostgreSQL

Each microservice can use its own database following the Database-per-Service pattern.

Example:

PostgreSQL
│
├── companydb
├── jobdb
└── reviewdb

This keeps services independent and prevents direct access to another service's database.

🐳 Running with Docker

Make sure Docker is installed.

Start the infrastructure:

docker compose up -d

Check running containers:

docker ps

Stop the containers:

docker compose down
▶️ Running the Application
1. Start Config Server
cd configserver
./mvnw spring-boot:run
2. Start Eureka Server
cd eurekaserver
./mvnw spring-boot:run
3. Start CompanyMS
cd companyms
./mvnw spring-boot:run
4. Start JobMS
cd jobms
./mvnw spring-boot:run
5. Start ReviewMS
cd reviewms
./mvnw spring-boot:run
🔌 Default Ports
Service	Port
Config Server	8071
Eureka Server	8761
CompanyMS	8081
JobMS	8082
ReviewMS	8083
Zipkin	9411
PostgreSQL	5432
🔄 Service Communication

The services communicate through REST APIs.

Example:

JobMS
  │
  │ GET company information
  ▼
Eureka
  │
  │ discovers COMPANYMS
  ▼
CompanyMS
  │
  ▼
PostgreSQL

Service discovery removes the need to hard-code service IP addresses.

🧪 API Testing

You can test the REST APIs using:

Postman
Insomnia
cURL

Example:

curl http://localhost:8081/companies

Get jobs:

curl http://localhost:8082/jobs

Get reviews:

curl http://localhost:8083/reviews
🔐 Best Practices

This project follows several microservices principles:

Independent services
RESTful APIs
Database per service
Service discovery with Eureka
Centralized configuration
Distributed tracing
Environment-based configuration
Containerized infrastructure
Loose coupling between services
🚀 Future Improvements

Possible future improvements include:

API Gateway
Spring Security
JWT authentication
Dockerized microservices
Kubernetes deployment
Prometheus & Grafana monitoring
Circuit Breaker with Resilience4j
Kafka/RabbitMQ for asynchronous communication
CI/CD with GitHub Actions
Unit and integration testing
👨‍💻 Author

Demikha Zakaria

Java / Spring Boot Backend Developer

Technologies
Java
Spring Boot
Spring Cloud
Microservices
PostgreSQL
Docker
Git
REST APIs
Eureka
Config Server
Zipkin
📄 License

This project is intended for educational and portfolio purposes.
