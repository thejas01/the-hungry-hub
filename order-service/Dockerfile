FROM eclipse-temurin:17-jdk-alpine


WORKDIR /app

COPY target/order-service-*.jar app.jar

# Expose the port your service runs on
EXPOSE 9082

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]