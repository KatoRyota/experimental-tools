FROM openjdk:11-jdk-slim
COPY employees-app-*.jar /app/
WORKDIR /app/
ENTRYPOINT ["sh","-c", "java -jar employees-app-*.jar"]