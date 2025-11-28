FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /build
COPY ./src ./src
COPY pom.xml .
RUN mvn clean package -U -DskipTests


FROM eclipse-temurin:21-jre
COPY --from=builder /build/target/movie-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]