package org.example.model;

public class Message {
    public String action;
    public String name;
    public String type;
    public String description;
    public String id;

    public String getAction(){
        return action;
    }

    public String getType(){
        return type;
    }

    public String getDescription(){
        return description;
    }

    public String getName(){
        return name;
    }

    public String getId(){
        return id;
    }

    public void setAction(String action){
        this.action = action;
    }

    public void setType(String type){
        this.type = type;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setId(String id){
        this.id = id;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
