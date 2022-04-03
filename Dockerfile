FROM openjdk:8-alpine
EXPOSE 9000
COPY target/educational_website-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "educational_website-0.0.1-SNAPSHOT.jar"]