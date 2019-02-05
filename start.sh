#!/bin/bash

gnome-terminal --title="consul-discovery" -- docker run --net=host -p 8500 consul agent -server -bind 127.0.0.1 -bootstrap-expect 1

./wait-for-it.sh 127.0.0.1:8500 --timeout=120
EXIT=$?
if [ $EXIT -eq 0 ]
then
    gnome-terminal --title="openzipkin-metrics" -- docker run --net=host -p 9411 openzipkin/zipkin
fi


./wait-for-it.sh 127.0.0.1:8500 --timeout=120
EXIT=$?
if [ $EXIT -eq 0 ]
then
  gnome-terminal --title="config-service" -- docker-compose -f docker-compose-config.yml up
fi

 ./wait-for-it.sh 127.0.0.1:8345 --timeout=120
EXIT=$?
if [ $EXIT -eq 0 ]
then
  gnome-terminal --title="dashboard-service" -- docker-compose -f docker-compose-dashboard.yml up
fi

 ./wait-for-it.sh 127.0.0.1:8345 --timeout=120
EXIT=$?
if [ $EXIT -eq 0 ]
then
  gnome-terminal --title="authentication-service" -- docker-compose -f docker-compose-auth.yml up
fi

 ./wait-for-it.sh 127.0.0.1:8345 --timeout=120
EXIT=$?
if [ $EXIT -eq 0 ]
then
 gnome-terminal --title="backend-service" -- docker-compose -f docker-compose-backend.yml up
fi

# ./wait-for-it.sh 127.0.0.1:8345 --timeout=120
#EXIT=$?
#if [ $EXIT -eq 0 ]
#then
# gnome-terminal --title="backend-2-service" -- docker-compose -f docker-compose-backend-2.yml up
#fi

 ./wait-for-it.sh 127.0.0.1:8081 --timeout=120
EXIT=$?
if [ $EXIT -eq 0 ]
then
  gnome-terminal --title="gateway-service" -- docker-compose -f docker-compose-gateway.yml up
fi



exit 0