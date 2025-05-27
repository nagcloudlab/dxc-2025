ARG MAVEN_VERSION=3.8.7
FROM maven:${MAVEN_VERSION}-amazoncorretto-17 as stage1
WORKDIR /app
COPY pom.xml /app
RUN mvn dependency:go-offline
COPY src /app/src
RUN mvn package -DskipTests

FROM amazoncorretto:17
WORKDIR /app
COPY --from=stage1 /app/target/java-web-service-0.0.1-SNAPSHOT.jar /app/java-web-service-0.0.1-SNAPSHOT.jar
EXPOSE 8080
USER root
ENV SPRING_PROFILES_ACTIVE=dev
ENTRYPOINT ["java", "-jar", "java-web-service-0.0.1-SNAPSHOT.jar"]
CMD ["--server.port=8080"]