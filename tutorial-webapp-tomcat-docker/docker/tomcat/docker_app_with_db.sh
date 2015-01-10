# build and run a dockerized image of the app that needs a database
rm *.war 
pushd ../../
mvn clean install -Pdockerized -Dspecific
popd
cp ../../target/tutorial-webapp-tomcat-docker-*.war .

# rm previous image
docker rmi "itemsapp:0.1"

# create image
docker build --tag="itemsapp:0.1" .

# create and run database container
docker run --name="itemsapp_db" postgres

# create and run application container
docker run --name="itemsapp" --link itemsapp_db:db -P itemsapp:0.1 "myself/mytomcat:0.1"


