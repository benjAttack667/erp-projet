package fr.ece.project.controllers;

import fr.ece.project.services.AuthService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

import java.awt.*;
import java.io.IOException;


public class LoginController {
    @FXML private TextField usernameField;
    @FXML private TextField passwordField;
    @FXML
    private Label labelField;
    @FXML private Hyperlink connexionLink;
    @FXML private Hyperlink inscriptionLink;



public void redirectInscription(ActionEvent e) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fr/ece/project/signIn.fxml"));
    usernameField.getScene().setRoot(fxmlLoader.load());
}
public void redirectConnexion(ActionEvent e) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fr/ece/project/login.fxml"));
    usernameField.getScene().setRoot(fxmlLoader.load());
}
    private final AuthService authService = new AuthService();
    public void initialize() {
        labelField.setText("");
    }

    @FXML
    public void onLogin(ActionEvent e) {


        if(usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
            labelField.setText("Veuillez remplir tous les champs.");
            return;
        }
        boolean ok = authService.login(usernameField.getText(), passwordField.getText());
        if (ok) {
           labelField.setText("Conneion reussie.");
        } else {
            labelField.setText("Verifiez vos informations.");
        }
    }
}

