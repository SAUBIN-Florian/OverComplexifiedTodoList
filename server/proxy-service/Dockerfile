FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/*.jar /app/main.jar

EXPOSE 8000

CMD ["java", "-jar", "main.jar"]
