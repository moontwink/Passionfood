package models;



public class Procedure {
 
    private int id;
    private String description;
 
    public Procedure(){}
 
    public Procedure(String description) {
        super();
        this.description = description;
    }
 
    //getters & setters
 
    @Override
    public String toString() {
        return "Procedure [id=" + id + ", description=" + description + "]";
    }
}