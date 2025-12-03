package fr.ece.project.models;

public class Admin extends User{
    public Admin(int id, String username, String password, String role) {
        super(id, username, password);
        this.role = "admin";
    }
}
