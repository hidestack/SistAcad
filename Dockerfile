# Use uma imagem do Maven para compilar a aplicação
FROM maven:3.9.5-eclipse-temurin-21 AS build

WORKDIR /app
COPY . /app

# Compile a aplicação Spring Boot
RUN mvn clean package -DskipTests

# Use uma imagem JDK para executar a aplicação
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8082

# Execute a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]