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

#### __Pets__

* __Retrieve all pets__  

        curl http://localhost:8080/v1/pets
  
   Returns a list of all pets in JSON format.
   
    
* __Retrieve pet by ID__
        
        curl http://localhost:8080/v1/pets/1
   
   Returns the details of a particular pet based on the petId provided (as a parameter) with HTTP response code 200. If a pet is not found HTTP response code 404 is returned.  
   
   
* __Add pet__  

        curl -X POST -H "Content-Type: application/json" \
        -d '{"petAge":4, "petId":3, "petName": "Bella", "petType": "Horse"}' \
        http://localhost:8080/v1/pets/add
         
    Requires the client to POST the pet details in JSON format and returns HTTP response code 201 if a pet is successfully added. Returns response code 409 if a pet with the         same petId already exists.
    

* __Update pet__ 
        
        curl -X PUT -H "Content-Type: application/json" \
        -d '{"petAge":4, "petId":3, "petName": "Coco", "petType": "Horse"}' \
        http://localhost:8080/v1/pets/update/3

    Requires the client to provide the updated pet details in JSON format along with the petId as a parameter, and returns HTTP response code 200 if the pet details are             successfully updated. HTTP response code 304 is returned if the update is unsuccessful.
    
        
* __Delete pet__ 
        
        curl -X DELETE http://localhost:8080/v1/pets/delete/1
  
    Deletes a pet based on the petId provided (as a parameter) and returns HTTP response code 200.
    
* __Search for pets by name__ 

        curl http://localhost:8080/v1/pets/name/Tom 
    
    Returns all pets with a matching petName in JSON format with HTTP response code 200 based on the petName provided (as a parameter).
   
        
* __Search for pets by age__ 

        curl http://localhost:8080/v1/pets/age/2

    Returns all pets with a matching petName in JSON format with HTTP response code 200 based on the petAge provided (as a parameter).


#### __Pet-Types__

* __Retrieve all pet-types__  

        curl http://localhost:8080/v1/pets-types
  
   
    
* __Retrieve pet-types by ID__
        
        curl http://localhost:8080/v1/pets-types/1
   
   
   
* __Add pet-type__  

        curl -X POST -H "Content-Type: application/json" \
        -d '{"petTypeName": "Bird"}' \
        http://localhost:8080/v1/pets-types/add
         
      

* __Update pet-type__ 
        
        curl -X PUT -H "Content-Type: application/json" \
        -d '{"petTypeName": "Fish"}' \
        http://localhost:8080/v1/pets-types/update/4

    
        
* __Delete pet-type__ 
        
        curl -X DELETE http://localhost:8080/v1/pets-types/delete/4
  
   

