package fr.ece.project.models;

    public class Manager extends User {
        private String role;
        public Manager(String id, String username, String password, String role) {
        super(id, username, password);
        this.role = "manager";
    }
}
