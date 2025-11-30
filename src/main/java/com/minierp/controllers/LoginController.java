package com.minierp.controllers;



import com.minierp.services.AuthService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML private TextField usernameField;
    @FXML private TextField passwordField;

    private final AuthService authService = new AuthService();

    @FXML
    public void onLogin(ActionEvent e) {
        boolean ok = authService.login(usernameField.getText(), passwordField.getText());
        if (ok) {
            System.out.println("Connexion réussie !");
        } else {
            System.out.println("Échec !");
        }
    }
}