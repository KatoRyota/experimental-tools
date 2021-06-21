#!/bin/bash

cd /app/experimental-tools/ || exit 1
: > stdout
./mvnw install >> stdout 2>&1 < /dev/null || exit 1
./mvnw spring-boot:run -pl chat-api >> stdout 2>&1 < /dev/null &
./mvnw spring-boot:run -pl employees-api >> stdout 2>&1 < /dev/null &
wait
echo "終了"
