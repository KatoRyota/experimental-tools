FROM openjdk:11-jdk-slim

# ---- ユーザーとグループの追加 ----
RUN groupadd -g 61000 docker && \
    useradd -l -m -s /bin/false -g docker -u 61000 docker

# ---- ユーザーの変更 ----
USER docker

RUN mkdir -vp ~/.m2

# ---- アプリケーションの配置と起動 ----
COPY --chown=docker:docker . /app/experimental-tools

WORKDIR /app/experimental-tools/
RUN chmod u+x mvnw

# CMD ["sh", "-c", "./mvnw install -pl core-modules -am && ./mvnw spring-boot:run -pl employees-api"]
CMD ["/bin/bash"]
