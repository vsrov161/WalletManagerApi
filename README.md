# WalletManagerApi
test task

### Launch instructions
1. `mvn clean package`
2. `cd docker`
3. `docker-compose up --build`

### Tech specs
- Java 17
- Spring Boot 3.5.13
- PostgreSQL 16
- Liquibase
- Docker
- Thymeleaf
- Swagger

### Web
- http://localhost:8085/status
- http://localhost:8085/swagger-ui/index.html

### Todos - wiil be done in future
- improve start up the application with jar
- improve service logic
- use BigDecimal
- improve Swagger UI descriptions
- improve tests providing success and failure scenarois of financial operations
