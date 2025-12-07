package fr.ece.project.controllers;

import fr.ece.project.services.AuthService;
import fr.ece.project.services.SceneManager;
import fr.ece.project.services.SessionManager;
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
            SessionManager.login(); //dit au Sessionmanager qu'on est connecté
            System.out.println("Connexion réussie !");
            SceneManager.switchScene("/views/dashboard.fxml", e);

        } else {
            System.out.println("Échec !");
        }
    }
}

