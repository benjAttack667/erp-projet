package fr.ece.project.controllers;

import fr.ece.project.dao.UserDAO;
import fr.ece.project.models.User;
import fr.ece.project.services.AuthService;
import fr.ece.project.services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

import java.io.IOException;

public class SignInController {
    @FXML
    private TextField nameField;
    @FXML
    private TextField surnameField;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Button signInButton;
    @FXML
    private Label label;
    @FXML
    private Hyperlink connexionLink;
    @FXML private Hyperlink inscriptionLink;






    @FXML
    public void redirectConnexion(ActionEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fr/ece/project/login.fxml"));
        nameField.getScene().setRoot(fxmlLoader.load());
    }
    @FXML
    public void redirectInscription(ActionEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fr/ece/project/signIn.fxml"));
        nameField.getScene().setRoot(fxmlLoader.load());
    }

    private final UserService userService = new UserService();





    public void initialize() {
        label.setText("");
    }

    @FXML
    public void onSignIn(ActionEvent e) throws IOException {

        if (nameField.getText().isEmpty() ||
                surnameField.getText().isEmpty() ||
                emailField.getText().isEmpty() ||
                passwordField.getText().isEmpty() ||
                datePicker.getValue() == null) {

            label.setText("Veuillez remplir tous les champs.");
            return;
        }

        User newUser = new User(
                nameField.getText(),
                surnameField.getText(),
                emailField.getText(),
                passwordField.getText(),
                "ADMIN"  // ou Admin si tu veux
        );

        boolean ok = userService.createUser(newUser);

        if (ok) {
            label.setText("Inscription réussie.");
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fr/ece/project/login.fxml"));
            signInButton.getScene().setRoot(fxmlLoader.load());
        } else {
            label.setText("Erreur lors de l'inscription.");
        }
    }

    }

