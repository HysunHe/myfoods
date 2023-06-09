#!/bin/sh

echo "Begin..."

mvn clean package && java -jar target/myfoods-service.jar

echo "Done\n"
