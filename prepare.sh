#!/bin/bash

echo `mvn clean package`;

echo `docker rm -f $(docker ps -a -q)`;

echo `docker rmi -f $(docker images -a)`;

