FROM eclipse-temurin:17-jre
WORKDIR /app
COPY target/issue-return-service-1.0.0.jar app.jar
EXPOSE 8095
ENTRYPOINT ["java","-jar","app.jar"]
