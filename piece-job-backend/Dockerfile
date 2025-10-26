FROM maven:3.9.9-eclipse-temurin-24 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean

FROM openjdk:24-jdk-slim
EXPOSE 8080
ADD target/piece-job-back-end.jar piece-job-back-end.jar
ENTRYPOINT ["java","-jar","/piece-job-back-end.jar" ]