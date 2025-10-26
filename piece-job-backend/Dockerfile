FROM openjdk:24-jdk-slim
EXPOSE 8080
ADD target/piece-job-back-end.jar piece-job-back-end.jar
ENTRYPOINT ["java","-jar","/piece-job-back-end.jar" ]