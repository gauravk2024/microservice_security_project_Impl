FROM openjdk:17
COPY target/patient-service-0.0.1-SNAPSHOT.jar /app/patient-service.jar
WORKDIR /app
EXPOSE 8081
CMD ["java", "-jar", "patient-service.jar"]