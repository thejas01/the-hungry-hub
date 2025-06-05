# The Hungry Hub

A microservices-based food delivery application built with Spring Boot and Kafka.

## Services

- **User Service**: Handles user management and authentication
- **Order Service**: Manages order processing and tracking
- **Payment Service**: Processes payments and transactions
- **Notification Service**: Handles system notifications and alerts
- **Restaurant Service**: Manages restaurant data and menus
- **Delivery Service**: Handles delivery assignments and status updates
- **API Gateway**: Routes requests to appropriate services
- **Eureka Server**: Service discovery for microservices

## Prerequisites

- Java 17+
- Maven 3.8+
- Docker
- Kafka
- MySQL

## Setup & Running

### 1. Start Dependencies

Start Zookeeper, Kafka, and MySQL (can use Docker or local installations).

```sh
# Start Zookeeper & Kafka (example with Homebrew)
brew services start zookeeper
brew services start kafka

# Or use docker-compose
docker-compose up -d

2.Start Eureka Server


cd eureka-server
./mvnw spring-boot:run


3. Start Microservices
Start each service in a separate terminal:


cd user-service
./mvnw spring-boot:run

cd order-service
./mvnw spring-boot:run

cd payment-service
./mvnw spring-boot:run

cd notification-service
./mvnw spring-boot:run

cd restaurant-service
./mvnw spring-boot:run

cd delivery-service
./mvnw spring-boot:run

cd api-gateway
./mvnw spring-boot:run

## API Documentation

### Service URLs
- User Service: http://localhost:8081
- Order Service: http://localhost:8082
- Payment Service: http://localhost:8083
- Notification Service: http://localhost:8084
- Restaurant Service: http://localhost:8087
- Delivery Service: http://localhost:8088
- API Gateway: http://localhost:8080

### API Endpoints

#### API Gateway Service
- **GET** `/gateway/health` - Health check endpoint for the API Gateway

#### User Service
- **POST** `/users` - Register a new user
- **GET** `/users/{id}` - Get user details by ID

#### Restaurant Service
- **POST** `/restaurants` - Add a new restaurant
- **GET** `/restaurants` - Get all restaurants
- **GET** `/restaurants/{id}` - Get restaurant details by ID
- **POST** `/restaurants/{restaurantId}/menu` - Add menu item to restaurant
- **GET** `/restaurants/{restaurantId}/menu` - Get menu items for restaurant

#### Order Service
- **POST** `/orders` - Place a new order
- **GET** `/orders/user/{userId}` - Get all orders for a user
- **POST** `/orders/place/{restaurantId}` - Place order for specific restaurant

#### Payment Service
- **POST** `/payments` - Process a payment

#### Delivery Service
- **GET** `/deliveries/user/{userId}` - Get all deliveries for a user
- **PUT** `/deliveries/{id}/status?status={status}` - Update delivery status

#### Notification Service
- No REST endpoints (Event-driven service using Kafka)

Kafka Topics
payment-events: Used for payment and notification communication
Database
MySQL is used for persistent storage. Update connection details in each service's application.properties.
Notes
Ensure all services are registered with Eureka for service discovery.
Kafka and MySQL must be running before starting the services.
Use the API Gateway for unified access to backend services.
