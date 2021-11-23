# neo4j_api

## Overview
This program creates a REST API that allows users to input and read data from a Neo4j database.  

The database is constructed of nodes corresponding to employee data 
- name: employee name 
- emp_id: employee ID


**NOTE:** The default database connected to the API is a sandbox instance that expires at 6:15PM on 11/25/21.  
If attempting to use the API after this date, please update the uri, username, and password as specified in the **Changing the Connected Database** section below.

## Instructions

### Run the Application

To launch and run the program, run the following command in a terminal window (in the neo4j_api directory):
> ./mvnw spring-boot:run

Now that the API is up and running, the user can interact with the Neo4j database.  
### Return All Nodes

The API endpoint for returning nodes is called "allnodes"

To return all nodes, the user can either:
1. Access the endpoint via web browser
> http://localhost:8080/allnodes

  - NOTE: The user can change which database the API is connected to by adding uri, username, and password parameters to the localhost address
  > Example: http://localhost:8080/allnodes?uri=YOUR_URI&username=YOUR_USERNAME&password=YOUR_PASSWORD

2. Access the endpoint via terminal with cURL. In a terminal window, run the command:
> curl localhost:8080/allnodes



### Add a Node

The API endpoint for adding a node is called "addnode"

To add a node, the user should use the following cURL command in a terminal window:
> curl --header "Content-Type: application/json" --request POST --data '{"name":"EMPLOYEE_NAME","id":EMPLOYEE_ID}' localhost:8080/addnode

<br/><br/> 
## Changing the Connected Database
- In order to change which database is connected to the API, the user will have to update the application.properties file in the 
neo4j_api/src/main/resources directory to include the correct uri, username, and password for the database.  


- The user will also have to update the uri, username, and password variables in the EmployeeController class 
(neo4j_api/src/main/java/com/example/neo4j_api/EmployeeController.java).  These variables are instantiated on lines 19-21.


- Additionally, the user will have to update the uri, username, and password defaultValues in the returnGetResponse function within the EmployeeController class (line 54)


