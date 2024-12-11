# Etapa 1: Construção da aplicação com Maven
FROM maven:3.9.0-eclipse-temurin-21 AS build

# Definir o diretório de trabalho no contêiner
WORKDIR /app

# Copiar o arquivo pom.xml e o diretório src para o contêiner
COPY pom.xml .
COPY src ./src

# Construir o JAR da aplicação
RUN mvn clean package -DskipTests

# Etapa 2: Execução da aplicação com OpenJDK
FROM eclipse-temurin:21-jdk AS runtime

# Definir o diretório de trabalho para a aplicação
WORKDIR /app

# Copiar o JAR construído da etapa anterior
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar app.jar

# Expôr a porta em que o Spring Boot vai rodar
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
