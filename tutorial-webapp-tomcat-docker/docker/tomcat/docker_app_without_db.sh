# build and run a dockerized image of the app that needs a database
rm *.war 
pushd ../../
mvn clean install
popd
cp ../../target/tutorial-webapp-tomcat-docker-*.war .

# rm previous image
docker rmi "itemsapp:0.1"

# create image
docker build --tag="itemsapp:0.1" .

# create and run application container
docker run --name="itemsapp" -P itemsapp:0.1 "myself/mytomcat:0.1"
