
# The Hungry Hub

A microservices-based food delivery application built with Spring Boot and Kafka.

## Services

- **User Service**: Handles user management and authentication
- **Order Service**: Manages order processing and tracking
- **Payment Service**: Processes payments and transactions
- **Notification Service**: Handles system notifications and alerts

## Prerequisites

- Java 17+
- Maven 3.8+
- Docker
- Kafka

## Setup & Running

### 1. Start Kafka

```bash
# Start Zookeeper & Kafka
brew services start zookeeper
brew services start kafka

cd user-service
./mvnw spring-boot:run

cd order-service
./mvnw spring-boot:run

cd payment-service
./mvnw spring-boot:run

cd notification-service
./mvnw spring-boot:run

API Documentation

User Service: http://localhost:8081/swagger-ui.html
Order Service: http://localhost:8082/swagger-ui.html
Payment Service: http://localhost:8083/swagger-ui.html
Notification Service: http://localhost:8084/swagger-ui.html

