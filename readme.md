## Description 
**Electronic Monkey** ![Monkey](https://user-images.githubusercontent.com/35753718/133340451-139c78fe-6f43-4359-b5f2-fdc2921bffd3.PNG) is an Online buy and sell application, built with Spring Boot for the backend and Angular for the frontend.

## Requirements
1. Java 11
2. Maven 3.x.x
3. MongoDB 5.0
4. MySQL 8.0

## Steps to Setup
1. Clone the application
git clone https://gitlab-cgi.stackroute.in/cgi-canada-wave1-capstone-projects/onlinebuyandsell.git

1. Start MySQL service and MongoDB service in your local machine
(the password for My SQL should be password by default otherwise you can set it in the file application.properties in userservice)

2. Build and run the backend app using maven with IntelliJ IDEA :
    *     If it is the first run, then run `mvn clean install`.
    *     Run eureka-service. It will start at http://localhost:8761/.
    *     Run api-gateway-service. It will start at http://localhost:8080/.
    *     Run userservice. It will start at http://localhost:8090/.
    *     Run catalogservice. It will start at http://localhost:8800/.
    *     Run orderservice. It will start at http://localhost:8880/.

3. Run the frontend app using npm with VSCode
    *  `cd webApp`    
    *  `npm install`
    *  Run `ng serve -o` for a dev server. Navigate to http://localhost:4200/. The app will automatically reload if you change any of the source files.
    ![Login](https://user-images.githubusercontent.com/35753718/133340485-c56a0449-b4c4-4341-a2cd-bac9e24c8c81.png)




    * Click the sign up button and fill the form to create an acount. 
    ![Signup](https://user-images.githubusercontent.com/35753718/133340502-10c8021f-8ccf-4160-aa83-b3b2f4af1a7f.png)
    


    
    * Login in with the new account to use the app ! 
    ![Dashboard](https://user-images.githubusercontent.com/35753718/133340510-82e583b9-4389-4405-9948-19f98b849c88.PNG)

## Swagger API
For API Documentation and direct interactions with the backend app using Swagger, navigate to **http://localhost:xxxx/swagger-ui/**                                                                                                                                     
xxxx is the port number for the microservice you want to interact with.
