package fr.ece.project.controllers;

import fr.ece.project.services.AuthService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML private TextField emailField;
    @FXML private TextField passwordField;

    private final AuthService authService = new AuthService();

    @FXML
    public void onLogin(ActionEvent e) {
        boolean ok = authService.login(emailField.getText(), passwordField.getText());
        if (ok) {
            System.out.println("Connexion réussie !");
        } else {
            System.out.println("Échec !");
        }
    }
}

