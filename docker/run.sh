#!/bin/sh

docker kill $(docker ps -q)
docker container remove spring-demo-full
docker compose up
