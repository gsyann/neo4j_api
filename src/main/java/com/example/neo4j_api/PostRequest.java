package com.example.neo4j_api;

public class PostRequest {
    int id;
    String name;

    public int getId(){
        return id;
    }
    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
