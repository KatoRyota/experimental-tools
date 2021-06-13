# Dockerによるローカル環境構築手順

## 概要

本ドキュメントはDockerを利用して、experimental-toolsのローカル環境を構築する為の手順です。

## 前提

本ドキュメントは以下の環境における構築手順です。  
しかし、他の環境でも同様の手順で、構築できるはずです。

* Windows 10
* WSL 2 (Ubuntu 20.04 LTS)
* Docker Desktop 3
* JDK 11
* Maven 3

## アプリケーションのビルド

親プロジェクトのルートディレクトリで、以下のコマンドを実行。

```shell
# [Windows]
mvn clean package -Dmaven.test.skip=true
```

## アプリケーションの配置

```shell
# [Ubuntu]
mkdir -vp ~/app
cd ~/app/
rm -vrf  distribution-0.0.1-SNAPSHOT*
cp -vip `wslpath -u 'C:\Users\kator\repo\experimental-tools\distribution\target\distribution-0.0.1-SNAPSHOT-local-bin.tar.gz'` ~/app/
tar xzvf distribution-0.0.1-SNAPSHOT-local-bin.tar.gz
```

## Dockerコンテナの作成/起動

```shell
# [Ubuntu]
cd ~/app/distribution-0.0.1-SNAPSHOT/
docker-compose up --build -d
docker-compose logs -f
```

## 動作確認

```shell
# [Ubuntu]
curl -s -X GET  "http://localhost:50001/employees"
curl -s -X GET  "http://localhost:50002/getLatestMessages?requestId=000-000-000&threadId=10&latestMessageId=20"
```

## Dockerコンテナの停止/削除

```shell
# [Ubuntu]
cd ~/app/distribution-0.0.1-SNAPSHOT/
docker-compose down
```

# Tips

## Dockerコンテナを一括で作成/起動したい

```shell
# [Ubuntu]
cd ${DOCKER_COMPOSE_YML_DIR}
docker-compose up --build -d
docker-compose logs -f
```

## Dockerコンテナを一括で停止/削除したい

```shell
# [Ubuntu]
cd ${DOCKER_COMPOSE_YML_DIR}
docker-compose down
```

## Dockerリソースを一括で削除したい

```shell
# [Ubuntu]
docker container stop `docker ps -q`
docker system prune -a --volumes
```

## Dockerコンテナの一覧を確認したい

```shell
# [Ubuntu]
docker container ls -a
```

## Dockerイメージの一覧を確認したい

```shell
# [Ubuntu]
docker image ls -a
```

## Dockerネットワークの一覧を確認したい

```shell
# [Ubuntu]
docker network ls
```

## Dockerボリュームの一覧を確認したい

```shell
# [Ubuntu]
docker volume ls
```

## Dockerコンテナでインタラクティブシェルを起動したい

```shell
# [Ubuntu]
docker container exec -it ${CONTAINER_NAME} /bin/bash
```

## Dockerコンテナのログを確認したい

```shell
# [Ubuntu]
docker container logs -f ${CONTAINER_NAME}
```

## Dockerコンテナの詳細を確認したい

```shell
# [Ubuntu]
docker container inspect ${CONTAINER_NAME}
```

## Dockerfileを作成したい

### for ubuntu

```shell
# [Ubuntu]
docker container run --dns=8.8.8.8 --rm \
    --name=ubuntu18-04 --hostname=ubuntu18-04 \
    -itd ubuntu:18.04

# コンテナに入って、手動で環境構築（インストールなど）を行っていき、その手順をDockerfileに記載する。
docker container exec -it ubuntu18-04 /bin/bash
```

### for centos

```shell
# [Ubuntu]
docker container run --dns=8.8.8.8 --rm \
    --name=centos7 --hostname=centos7 \
    -itd centos:7 /sbin/init
  
# コンテナに入って、手動で環境構築（インストールなど）を行っていき、その手順をDockerfileに記載する。
docker container exec -it centos7 /bin/bash
```

## Dockerfileからイメージをビルドして起動したい

```shell
# [Ubuntu]
cd ${DOCKERFILE_DIR}
docker image build -t ${IMAGE_NAME}:${VERSION} .
docker container run --dns=8.8.8.8 --rm \
    --name=${CONTAINER_NAME} --hostname=${HOST_NAME} \
    -itd ${IMAGE_NAME}:${VERSION}

docker container exec -it ${CONTAINER_NAME} /bin/bash
```
