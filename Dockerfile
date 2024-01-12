FROM maven:3.9.6-amazoncorretto-17 as build
COPY ./ /app
WORKDIR /app
RUN mvn clean compile

FROM build as package
RUN mvn package

FROM amazoncorretto:17.0.9-alpine as start
COPY --from=package /app/target/*.jar /app/app.jar
EXPOSE 8081
ENTRYPOINT java -jar /app/app.jar