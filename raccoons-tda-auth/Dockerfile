FROM gradle:6.2.1-jdk11 AS build

# Copy everything from the current directory to /home/gradle/src and set the owner to the 'gradle' user
COPY --chown=gradle:gradle . /home/gradle/src

# Change the directory
WORKDIR /home/gradle/src

# Build from the current working directory
RUN gradle build --no-daemon

# Use a Java 11 JRE
FROM openjdk:11-jre-slim

EXPOSE 9876

RUN mkdir /app/security

# keytool -import -alias raccoons_tda_auth -file myCertificate.crt -keystore keystore.p12 -storepass password
# RUN keytool -import -alias raccoons_tda_auth -file myCertificate.crt -keystore keystore.p12 -storepass password

# Copy all jars from the libs directory and place it in the app directory
COPY --from=build /home/gradle/src/build/libs/*.jar /app/raccoons-tda-auth-service.jar

# Execute the application
ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/raccoons-tda-auth-service.jar"]