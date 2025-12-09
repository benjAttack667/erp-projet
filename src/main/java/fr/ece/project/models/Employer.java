package fr.ece.project.models;

public class Employer extends User {
    private String role;
    public Employer(String username, String password, String role){
        super(username, password);
        this.role = "EMPLOYER";
    }
}
