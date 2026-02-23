# ---- Build stage ----
FROM eclipse-temurin:21-jdk AS builder

WORKDIR /build

# Maven Wrapper (so Docker build doesn't depend on host Maven)
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

RUN chmod +x mvnw
RUN ./mvnw -q -DskipTests dependency:go-offline

# App sources
COPY src ./src

# Build
RUN ./mvnw -q -DskipTests clean package

# ---- Runtime stage ----
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=builder /build/target/app.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]