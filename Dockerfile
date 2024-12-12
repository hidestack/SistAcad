FROM maven:3.9.5-eclipse-temurin-17 AS build

# Set working directory
WORKDIR /app
COPY . /app

# Build the application (assuming application class is in src/main/java)
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar app.jar

# Expose port for the application
EXPOSE 8081

# Start the application using the JAR file
CMD [ "java", "-jar", "app.jar" ]