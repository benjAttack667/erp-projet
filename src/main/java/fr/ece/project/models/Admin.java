package fr.ece.project.models;

public class Admin extends User{
    public Admin(String username, String password, String role) {
        super(username, password);
        this.role = "admin";
    }
}
