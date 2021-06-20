FROM openjdk:11-jdk-slim

# ---- ユーザーとグループの追加 ----
RUN groupadd -r docker && \
    useradd -r -g docker docker

# ---- ユーザーの変更 ----
USER docker

ENV MAVEN_OPTS="-Dmaven.repo.local=/app/.m2"

# ---- アプリケーションの配置と起動 ----
COPY --chown=docker:docker . /app/experimental-tools

WORKDIR /app/experimental-tools/
CMD ["sh","-c", "./mvnw spring-boot:run"]
