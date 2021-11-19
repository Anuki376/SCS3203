# PetStore API

Prerequisites: JavaJDK11, Git CLI.  
First clone the Git repo and proceed with the following instructions

## 1.	How to build and/or deploy the API

To package and build the application use: 

    ./gradlew build

This will produce the quarkus-run.jar file in the build/quarkus-app/directory. Then to run the application:

    java -jar build/petstore-runner.jar

It produces the `quarkus-run.jar` file in the `build/quarkus-app/` directory.
Be aware that it is not an _??ber-jar_ as the dependencies are copied into the `build/quarkus-app/lib/` directory.

To launch the test page, open your browser at the following URL

    http://localhost:8080/index.html
    
    
    
## 2. How to run test suite

Run the following command in the terminal 

    ./gradlew test
    
    

## 3. How to run a CURL/WGET command to test the APIs once deployed 

#### __Pet__

* __Retrieve all pets__  

        curl http://localhost:8080/v1/pets
  
   This method requires HTTP GET method and returns a list of all pets in JSON format.
   
    
* __Retrieve pet by ID__
        
        curl http://localhost:8080/v1/pets/{petId}
   
   This method returns the details of a particular pet based on the petId required from the client. If a matching pet is found the method will return the pet details in JSON 
   format with HTTP response code 200. If a pet is not found HTTP response code 404 will be returned.  
   
   
* __Add pet__  

        curl -d '{"petAge":4, "petId":3, "petName": "Bella", "petType": "Horse"}' -H "content-Type : application/json" -X POST http://localhost:8080/v1/pets/add
    
    This method requires the client to POST the pet details in JSON format. If a pet is successfully added HTTP response code 201 will be returned. If a pet with that Id already     exists HTTP response code 409 will be returned. 

* __Update pet__ 

    This method uses the PUT method and requires the client to post the pet details in JSON format alongwith the relevant petId as a parameter. If the pet details are               successfully updated HTTP response code 200 will be returned. If no pet exists for that petId, response code 404 will be returned.
        
* __Delete pet__ 

    This method uses the DELETE method and requires the client to provide the petId of the pet that should be deleted. If the pet is successfully deleted HTTp response code 200     will be returned and if no pet with a matching petId exists response code 404 will be returned. 

* __Search for pets by name__ 

        curl http://localhost:8080/v1/pets/name/{petName}
    
   This method requires the petName from the client as a parameter. All pets with the matching name will be returned in JSON format with HTTP response code 200.
        
* __Search for pets by age__ 

        curl http://localhost:8080/v1/pets/age/{petAge}

   This method requires the petAge from the client as a parameter. All pets of that age will be returned in JSON format with HTTP response code 200.




