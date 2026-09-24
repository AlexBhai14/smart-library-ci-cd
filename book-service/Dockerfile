FROM eclipse-temurin:17-jre
WORKDIR /app
COPY target/book-service-1.0.0.jar app.jar
EXPOSE 8093
ENTRYPOINT ["java","-jar","app.jar"]
