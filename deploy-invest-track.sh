echo "Stopping tomcat server..!"

catalina stop

echo "Moving to invest-track directory"

cd /Users/vasishta/Documents/Vasishta/Git/invest-track

echo "Running maven clean install"
mvn clean install -DskipTests

cd invest-track-service/target

echo "Renaming the war file"
mv invest-track-service-0.0.1-SNAPSHOT.war invest-track.war

echo "Copying the war to tomcat webapps directory"
cp invest-track.war /usr/local/Cellar/tomcat/9.0.40/libexec/webapps

echo "Starting tomcat server..!"
catalina start