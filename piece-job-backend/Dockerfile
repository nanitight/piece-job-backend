FROM maven:3.9.9-eclipse-temurin-24 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:24-jdk-slim
WORKDIR /app
EXPOSE 8080
COPY --from=build /app/target/*.jar piece-job-back-end.jar
ENTRYPOINT ["java","-jar","piece-job-back-end.jar" ]