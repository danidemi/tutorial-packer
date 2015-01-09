rm *.war 
pushd ../../
mvn clean install
popd
cp ../../target/tutorial-webapp-tomcat-docker-*.war .
docker rmi "itemapp:0.1"
docker build --tag="itemapp:0.1" .