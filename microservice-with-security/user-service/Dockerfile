FROM openjdk:17
COPY target/user-service-0.0.1-SNAPSHOT.jar /app/user-service.jar
WORKDIR /app
EXPOSE 8080
CMD ["java", "-jar", "user-service.jar"]