#!/bin/bash -xv

# create and run database container
docker stop itemsapp_db
docker rm itemsapp_db
docker run -d --name="itemsapp_db" -e POSTGRES_PASSWORD=postgres postgres

# build and run a dockerized image of the app that needs a database
rm *.war 
pushd ../../
mvn clean install -Pdockerized.db -Dspecific
popd
cp ../../target/tutorial-webapp-tomcat-docker-*.war .

# rm previous image and container
docker rm "itemsapp"
docker rmi -f "itemsapp:0.1"

# create image
docker build --tag="itemsapp:0.1" .

# create and run application container
docker run --name="itemsapp" -p 8080:8080 --link itemsapp_db:db -P itemsapp:0.1

# stop the db
docker stop itemsapp_db
docker rm itemsapp_db



