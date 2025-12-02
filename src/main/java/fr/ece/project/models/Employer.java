package fr.ece.project.models;

public class Employer extends User {
    private String role;
    public Employer(int id, String username, String password, String role){
        super(id, username, password);
        this.role = "employer";
    }
}
