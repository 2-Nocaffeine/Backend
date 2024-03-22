FROM openjdk:17-jdk-alpine3.13
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]