FROM openjdk:11-jdk-slim

# ---- ユーザーとグループの追加 ----
RUN groupadd -r docker && \
    useradd -r -g docker docker

# ---- ユーザーの変更 ----
USER docker

# ---- アプリケーションの配置と起動 ----
COPY --chown=docker:docker chat-api-*.jar /app/
COPY --chown=docker:docker config         /app/config
COPY --chown=docker:docker data           /app/data

WORKDIR /app/
CMD ["sh","-c", "java -jar chat-api-*.jar"]
