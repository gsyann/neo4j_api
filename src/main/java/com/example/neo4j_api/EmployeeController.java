package com.example.neo4j_api;

import org.neo4j.driver.*;
import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import static org.neo4j.driver.Values.parameters;

@RestController
public class EmployeeController {

    private final Driver driver;

    public String getResponse = "No response";

    // If using a different database than the one specified below, update these values
    public final String uri = "bolt://3.216.9.216:7687";
    public final String username = "neo4j";
    public final String password = "checkpoint-dam-arrays";


    public EmployeeController(Driver driver) {
        this.driver = driver;
    }


    public void setGetResponse(String getResponse) {
        this.getResponse = getResponse;
    }


    public String returnAll(Driver driver) {
        try (Session session = driver.session()) {
            session.writeTransaction(new TransactionWork<String>() {
                @Override
                public String execute(Transaction tx) {
                    Result result = tx.run("MATCH (p:Employee) RETURN collect({name: p.Name, emp_id: p.emp_id})");
                    String getResponse = result.single().values().get(0).toString();
                    setGetResponse(getResponse);
                    return getResponse;
                }
            });
            return getResponse;
        }
        catch (Exception e) {
            return "Failure reaching database";
        }
    }


    @GetMapping(path = "/allnodes", produces = MediaType.APPLICATION_JSON_VALUE)
    public String returnGetResponse(@RequestParam(value = "uri", defaultValue = "bolt://3.216.9.216:7687") String uri, @RequestParam(value = "user", defaultValue = "neo4j") String user, @RequestParam(value = "password", defaultValue = "checkpoint-dam-arrays") String password) {
        Driver driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
        String result = returnAll(driver);
        return result;
    }


    public void addEmployee(String employeeName, int emp_id, Driver driver) {
        try (Session session = driver.session()) {
            session.writeTransaction(new TransactionWork<String>() {
                @Override
                public String execute(Transaction tx) {
                    Result result = tx.run("CREATE (n:Employee {Name:$employeeName, emp_id: $emp_id})",
                            parameters("employeeName", employeeName, "emp_id", emp_id));
                    return "Finished";
                }
            });
        }
    }


    @PostMapping(value = "/addnode")
    public String addNode(@RequestBody PostRequest input) {
        try {
            Driver driver = GraphDatabase.driver(uri, AuthTokens.basic(username, password));
            if (input.getName() == null || input.getId() == 0) {
                throw new IllegalAccessException("Incorrect input");
            }
            addEmployee(input.getName(), input.getId(), driver);
            return "Successfully added 1 record to database";
        }
        catch (Exception e){
            return "Failure adding 1 record to database. Incorrect input format";
        }
    }
}