# build and run a dockerized image of the app that needs a database
rm *.war 
pushd ../../
mvn clean install -Dspecific -Pdockerized.memory
popd
cp ../../target/tutorial-webapp-tomcat-docker-*.war ./items.war

# rm previous image
docker rmi -f "itemsapp:0.1"

# create image
docker build --tag="itemsapp:0.1" .

# create and run application container
docker rm "itemsapp"
docker create --name="itemsapp" -P itemsapp:0.1
