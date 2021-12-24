set -e
cd besuplugin
gradle build
cp app/build/libs/app.jar /Users/zhuowei/Downloads/besu-21.10.5/plugins/Plugin.jar
