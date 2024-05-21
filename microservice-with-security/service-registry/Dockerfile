FROM openjdk:17
COPY target/service-registry-0.0.1-SNAPSHOT.jar /app/service-registry.jar
WORKDIR /app
EXPOSE 8761
CMD ["java", "-jar", "service-registry.jar"]