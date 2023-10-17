# Start with a base image containing Java runtime
FROM adoptopenjdk/openjdk11:latest

# Information around who maintains the image
MAINTAINER eazybytes.com

# Add the application's jar to the image
COPY target/accounts-1.0.0-SNAPSHOT.jar accounts-1.0.0-SNAPSHOT.jar

# Execute the application
ENTRYPOINT ["java", "-jar", "accounts-1.0.0-SNAPSHOT.jar"]