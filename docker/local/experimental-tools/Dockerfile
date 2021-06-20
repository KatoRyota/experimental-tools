FROM openjdk:11-jdk-slim

# ---- ユーザーとグループの追加 ----
RUN groupadd -g 61000 docker && \
    useradd -l -m -s /bin/false -g docker -u 61000 docker

# ---- ユーザーの変更 ----
USER docker

# ---- アプリケーションの配置と起動 ----
COPY --chown=docker:docker . /app/experimental-tools

WORKDIR /app/experimental-tools/
RUN chmod u+x mvnw

CMD ["sh", "-c", "./mvnw install && ./mvnw spring-boot:run -pl chat-api,employees-api"]
