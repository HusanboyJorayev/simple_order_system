FROM openjdk:17

ARG JAR_FILE=target/*.jar

COPY ./target/order_system.jar app.jar

EXPOSE 9090

ENTRYPOINT ["java", "-jar", "/app.jar"]
