FROM openjdk:11-jdk-slim

RUN \
    # ---- パッケージのインストール ----
    apt update && \
    DEBIAN_FRONTEND=noninteractive apt install -y \
        tzdata \
        locales \
        less && \
    apt clean && \
    rm -rf /var/lib/apt/lists/* && \
    # ---- タイムゾーンとロケールの設定 ----
    ln -sf /usr/share/zoneinfo/Asia/Tokyo /etc/localtime && \
    dpkg-reconfigure --frontend noninteractive tzdata && \
    sed -i -E 's/# (ja_JP.UTF-8)/\1/' /etc/locale.gen && \
    locale-gen && \
    # ---- ユーザーとグループの追加 ----
    groupadd -g 61000 docker && \
    useradd -l -m -s /bin/false -g docker -u 61000 docker

# ---- ユーザーの変更 ----
USER docker

ENV TZ="Asia/Tokyo" \
    LANG="ja_JP.UTF-8" \
    LANGUAGE="ja_JP:ja" \
    LC_ALL="ja_JP.UTF-8"

# ---- アプリケーションの配置と起動 ----
COPY --chown=docker:docker . /app/experimental-tools

WORKDIR /app/experimental-tools/

RUN mkdir -vp /home/docker/.m2 && \
    chmod u+x mvnw && \
    chmod u+x docker/local/experimental-tools/run.sh

WORKDIR /app/experimental-tools/docker/local/experimental-tools/
CMD ["./run.sh"]
# CMD ["/bin/bash"]
