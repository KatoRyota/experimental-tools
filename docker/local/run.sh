#!/bin/bash

cd /app/experimental-tools/
: > stdout
./mvnw install >> stdout 2>&1 < /dev/null
./mvnw spring-boot:run -pl chat-api >> stdout 2>&1 < /dev/null &
./mvnw spring-boot:run -pl employees-api >> stdout 2>&1 < /dev/null &
