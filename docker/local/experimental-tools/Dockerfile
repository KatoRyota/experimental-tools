FROM openjdk:11-jdk-slim

# ---- ユーザーとグループの追加 ----
RUN groupadd -g 61000 docker && \
    useradd -l -m -s /bin/false -g docker -u 61000 docker

# ---- ユーザーの変更 ----
USER docker

# ENV MAVEN_OPTS="-Dmaven.repo.local=/app/.m2"

# ---- アプリケーションの配置と起動 ----
COPY --chown=docker:docker . /app/experimental-tools

RUN chown u+x ./mvnw

WORKDIR /app/experimental-tools/
# CMD ["sh","-c", "./mvnw spring-boot:run"]
CMD ["/bin/bash"]
