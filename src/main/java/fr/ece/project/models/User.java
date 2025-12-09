package fr.ece.project.models;

import fr.ece.project.utils.HashUtil;

import java.util.UUID;

import static fr.ece.project.utils.HashUtil.hash;

public class User {
    protected String id;
    protected String name;
    protected String surname;
    protected String email;
    protected String passwordHash;
    protected String role;

    public User(String name, String surname, String email, String password, String role) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.role = role;


        this.passwordHash = HashUtil.hash(password);
    }

    public User() {}

    public User(String surname, String passwordHash) {
        this.id = UUID.randomUUID().toString();
        this.surname = surname;
        this.passwordHash = passwordHash;
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

    public String getSurname() { return surname; }
    public void setUsername(String username) { this.surname = username; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public void setId(String id) {
        this.id = id;
    }
}

