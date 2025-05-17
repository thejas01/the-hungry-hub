FROM eclipse-temurin:17-jdk-alpine

COPY target/restaurant-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 9002
ENTRYPOINT ["java","-jar","/app.jar"]