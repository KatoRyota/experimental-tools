#!/bin/bash

SHELL_SCRIPT_PATH=$0
SHELL_SCRIPT_FILE_NAME=$(basename $SHELL_SCRIPT_PATH)
SHELL_SCRIPT_DIR=$(cd $(dirname $SHELL_SCRIPT_PATH); pwd)
: > stdout

echo "[Start] $SHELL_SCRIPT_DIR/$SHELL_SCRIPT_FILE_NAME" >> stdout 2>&1

cd /app/experimental-tools/ || exit 1
./mvnw install >> stdout 2>&1 || exit 1
./mvnw spring-boot:run -pl chat-api >> stdout 2>&1 < /dev/null &
./mvnw spring-boot:run -pl employees-api >> stdout 2>&1 < /dev/null &
wait

echo "[End] $SHELL_SCRIPT_DIR/$SHELL_SCRIPT_FILE_NAME" >> stdout 2>&1
