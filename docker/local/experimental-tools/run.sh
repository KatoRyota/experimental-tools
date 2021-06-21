#!/bin/bash

SHELL_SCRIPT_PATH=$0
SHELL_SCRIPT_FILE_NAME=$(basename $SHELL_SCRIPT_PATH)
SHELL_SCRIPT_DIR=$(cd $(dirname $SHELL_SCRIPT_PATH); pwd)

cd /app/experimental-tools/ || exit 1

echo "[Start] $SHELL_SCRIPT_DIR/$SHELL_SCRIPT_FILE_NAME"

./mvnw install || exit 1
./mvnw spring-boot:run -pl chat-api &
./mvnw spring-boot:run -pl employees-api &
wait

echo "[End] $SHELL_SCRIPT_DIR/$SHELL_SCRIPT_FILE_NAME"
