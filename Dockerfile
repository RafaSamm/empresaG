# Stage de build

FROM maven:3.9.9-eclipse-temurin-21 AS empresabuild
WORKDIR /app

COPY pom.xml .
RUN mvn -B dependency:go-offline

COPY src ./src
RUN mvn -B package -DskipTests

# Stage de runtime

FROM eclipse-temurin:21-jre
WORKDIR /app

COPY --from=empresabuild /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]