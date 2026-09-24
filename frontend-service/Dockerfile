FROM eclipse-temurin:17-jre
WORKDIR /app
COPY target/frontend-service-1.0.0.jar app.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","app.jar"]
