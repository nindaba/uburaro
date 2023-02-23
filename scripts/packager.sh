timestamp=$(date +"%Y-%m-%dT%H:%M.%S")
mkdir ../scripts/$timestamp-app
mkdir ../scripts/$timestamp-app/app
mkdir ../scripts/$timestamp-app/server
mkdir ../scripts/$timestamp-app/server/logs

echo git pull
cd ../management-backoffice
echo npm install
npm run package

echo Moving release to app folder
mv release/* ../scripts/$timestamp-app/app

echo building spring
cd ../platform
./gradlew bootJar
mv build/libs/platform-3.0.jar ../scripts/$timestamp-app/server

echo starting the server
cd ../scripts/$timestamp-app/server
java -jar platform-3.0.jar & echo $! > ./logs/$timestamp.log &