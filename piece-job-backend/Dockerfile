FROM maven:3.9.9-eclipse-temurin-24 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:25-jdk
WORKDIR /app
EXPOSE 8080
COPY --from=build /app/target/*.jar piece-job-back-end.jar
ENTRYPOINT ["java","-jar","piece-job-back-end.jar" ]