package fr.ece.project.models;

import java.util.UUID;

import static fr.ece.project.utils.HashUtil.hash;

public class User {
    protected String id;
    protected String username;
    protected String passwordHash;
    protected String role;
    protected String email;
    protected String name;

    public User(String username, String role, String passwordHash) {
        this.username = username;
        this.role = role;
        this.passwordHash = passwordHash;
        this.email = email;
        this.name = name;
    }

    public User() {}

    public User(int id, String username, String passwordHash) {
        this.id = UUID.randomUUID().toString();
        this.username = username;
        this.passwordHash = hash(passwordHash);
        this.name = null;
        this.email = null;


    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() { return id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public void setId(String id) {
        this.id = id;
    }
}

