FROM openjdk:11-jdk-slim
COPY employees-api-*.jar /app/
WORKDIR /app/
ENTRYPOINT ["sh","-c", "java -jar employees-api-*.jar"]