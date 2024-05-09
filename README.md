# URL-Shortening-Service
URL Shortening Service

**#Tech-Stack-Used**

Java V 17

Spring Boot V 3.2.5

H2 DB V 2.2.224

Swagger Open API V 2.3.0

#Instructions to Run in local 

The Project is a simple Spring Boot Application and can be run directly from the IntelliJ Idea 

If IntelliJ Idea is not available and you have Java V 17 installed in your system, 

Open CMD prompt and head to the folder of the project, and run the following cmd 

mvnw clean install

Post Running you should see a successful message like this 

![image](https://github.com/aljwright/URL-Shortening-Service/assets/45116835/3bb41c17-623a-4d43-a29e-149fd2ca1f0b)


Then execute the following cmd 

cd ./target

and then run, 

java -jar .\url.shortening.service-0.0.1-SNAPSHOT.jar

Then you should see the following starup message, 

![image](https://github.com/aljwright/URL-Shortening-Service/assets/45116835/323e289e-db7e-4a5d-87fc-f4ed0a54ef7e)


Once started you can head to the following URL to access the Swagger, 

http://localhost/swagger-ui/index.html

You can also REST Clients like Postman to test your APIs. 




 
