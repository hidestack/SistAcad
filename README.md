# Demo Spring Boot Application with Docker and PostgreSQL

Este repositório contém um exemplo de aplicação Spring Boot com Docker e PostgreSQL. A aplicação é configurada para ser empacotada em um contêiner Docker, e é configurada para se conectar a um banco de dados PostgreSQL, também rodando em um contêiner Docker.

## Estrutura do Projeto

O projeto é estruturado da seguinte maneira:

- **`pom.xml`**: Arquivo de configuração Maven para gerenciar dependências e construir o projeto.
- **`Dockerfile`**: Arquivo de configuração para criar a imagem Docker da aplicação.
- **`docker-compose.yml`**: Arquivo para orquestrar os contêineres Docker da aplicação e do banco de dados PostgreSQL.

### Dependências

O projeto utiliza as seguintes dependências:

- **Spring Boot**: Framework para construção da aplicação.
- **Spring Data JPA**: Para interação com o banco de dados PostgreSQL.
- **Spring Web**: Para construção de APIs RESTful.
- **PostgreSQL**: Banco de dados utilizado pela aplicação.
- **Lombok**: Para simplificação de código, evitando boilerplate.
- **Spring Boot DevTools**: Para facilitar o desenvolvimento com reinicialização automática.

## Como Rodar a Aplicação

### Pré-requisitos

Certifique-se de que você tem as seguintes ferramentas instaladas:

- [Docker](https://www.docker.com/get-started)
- [Docker Compose](https://docs.docker.com/compose/install/)
- [Maven](https://maven.apache.org/install.html) (se for compilar a aplicação localmente)

### Passo a Passo

1. **Clone o repositório**:

   ```bash
   git clone https://github.com/seu-usuario/demo-spring-boot-docker.git
   cd demo-spring-boot-docker
   ```

2. **Construa e execute os contêineres Docker**:

   Use o comando a seguir para construir e iniciar os contêineres Docker:

   ```bash
   docker-compose up --build
   ```

   Este comando irá:

   - Construir a aplicação Spring Boot utilizando o Maven.
   - Criar dois contêineres Docker: um para a aplicação e outro para o banco de dados PostgreSQL.

3. **Acesse a aplicação**:

   Após a construção e inicialização dos contêineres, a aplicação estará disponível em:

   ```
   http://localhost:8082
   ```

4. **Banco de Dados PostgreSQL**:

   O banco de dados PostgreSQL estará disponível em:

   - Host: `localhost`
   - Porta: `5432`
   - Usuário: `postgres`
   - Senha: `root`
   - Banco de dados: `riven`

   Você pode usar essas credenciais para acessar o banco de dados via qualquer cliente PostgreSQL, como o [pgAdmin](https://www.pgadmin.org/) ou [DBeaver](https://dbeaver.io/).

### Arquitetura

- **Contêiner da aplicação (`demo-app`)**: Constrói a aplicação Spring Boot e a executa, expondo a porta `8082`.
- **Contêiner do banco de dados (`db`)**: Roda uma instância do PostgreSQL na versão `15-alpine`, configurada com as credenciais e o banco de dados especificado.
- **Volumes**: Os dados do banco de dados são persistidos através de um volume Docker chamado `db_data`, garantindo que os dados não sejam perdidos quando os contêineres forem removidos.

## Dockerfile

O `Dockerfile` configura duas fases de construção:

1. **Fase de Build**: Usa a imagem `maven:3.9.5-eclipse-temurin-21` para construir o projeto, empacotando a aplicação Spring Boot em um JAR.
   
2. **Fase de Execução**: Usa a imagem `eclipse-temurin:21-jdk-alpine` para rodar o JAR empacotado.

```dockerfile
# Fase de Build
FROM maven:3.9.5-eclipse-temurin-21 AS build

WORKDIR /app
COPY . /app

# Compile a aplicação Spring Boot
RUN mvn clean package -DskipTests

# Fase de Execução
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8082

# Execute a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
```

## docker-compose.yml

O `docker-compose.yml` orquestra os dois serviços:

- **`demo-app`**: Contêiner que executa a aplicação Spring Boot.
- **`db`**: Contêiner com o banco de dados PostgreSQL.

```yaml
version: '3.8'

services:
  demo-app:
    build:
      context: .
      dockerfile: Dockerfile
    ports:
      - "8082:8082"
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://db:5432/riven
      SPRING_DATASOURCE_USERNAME: postgres
      SPRING_DATASOURCE_PASSWORD: root
    depends_on:
      - db

  db:
    image: postgres:15-alpine
    container_name: riven-db
    environment:
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: root
      POSTGRES_DB: riven
    ports:
      - "5432:5432"
    volumes:
      - db_data:/var/lib/postgresql/data

volumes:
  db_data:
    driver: local
```

## Configuração do Banco de Dados

A aplicação Spring Boot se conecta ao banco de dados PostgreSQL através das seguintes variáveis de ambiente, que são passadas via Docker Compose:

- **SPRING_DATASOURCE_URL**: URL de conexão com o banco de dados (definido como `jdbc:postgresql://db:5432/riven`).
- **SPRING_DATASOURCE_USERNAME**: Usuário do banco de dados (definido como `postgres`).
- **SPRING_DATASOURCE_PASSWORD**: Senha do banco de dados (definido como `root`).

## Clone o repositório para a sua máquina local

```bash
https://github.com/hidestack/SistAcad.git
