# Dashboard application:
UI with themleaf
Backend with spring boot

Technical Stac:
Java-1.8
Spring-boot 1.5.9
MongoDB- 4.2

Steps: 
Mongodb needs to be in local: 
run docker using below command, docker should be installed in local environment, more info: https://docs.docker.com/docker-for-mac/install/

$ docker run --name my_mongo -d -p 127.0.0.1:27017:27017 mongo:4.2

clone the source code:
Do update teh wechat test acocuntid and api key to integrate with weChat in application.properties file.
DO the Changes in ScheduledTasks , enable the @Schedule on the methods.

Run the application 

mvn spring-boot:run


Remember to get the test account id and api key from wechat engine




