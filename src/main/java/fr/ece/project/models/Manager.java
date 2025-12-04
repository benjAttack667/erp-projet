package fr.ece.project.models;

    public class Manager extends User {
        private String role;
        public Manager( String username, String password, String role) {
        super(username, password);
        this.role = "manager";
    }
}
