
FROM maven:3.9.12-ibm-semeru-21-noble AS build

WORKDIR /app

COPY pom.xml .
RUN mvn -B dependency:go-offline   # pulls all dependencies, skips tests

COPY src ./src
RUN mvn -B clean package -DskipTests  # builds target/*.jar

FROM eclipse-temurin:21-jre-ubi10-minimal

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar
COPY . .

WORKDIR /app

EXPOSE 8080
EXPOSE 8081
HEALTHCHECK --interval=30s --timeout=5s \
    CMD wget -qO- http://localhost:8080/actuator/health || exit 1

#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]