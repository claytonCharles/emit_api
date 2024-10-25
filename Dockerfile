FROM maven:latest as build

COPY src /app/src
COPY pom.xml /app

WORKDIR /app
RUN mvn clean install

FROM openjdk:24-oracle

COPY --from=build /app/target/emit-api.jar /app/app.jar

WORKDIR /app

EXPOSE 9000

CMD ["java", "-jar", "app.jar"]