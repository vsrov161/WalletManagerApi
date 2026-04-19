FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/*.jar WalletManagerApi-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "WalletManagerApi-0.0.1-SNAPSHOT.jar"]