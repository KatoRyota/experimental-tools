#!/bin/bash

cd /app/experimental-tools/ && \
./mvnw install && \
./mvnw spring-boot:run -pl chat-api && \
./mvnw spring-boot:run -pl employees-api
