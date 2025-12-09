package fr.ece.project.controllers;

import fr.ece.project.dao.UserDAO;
import fr.ece.project.models.User;
import fr.ece.project.services.AuthService;
import fr.ece.project.services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class SignInController {

    // ==== Champs du formulaire FXML ====
    @FXML
    private TextField nameField; // Champ prénom
    @FXML
    private TextField surnameField; // Champ nom
    @FXML
    private TextField emailField; // Champ email
    @FXML
    private PasswordField passwordField; // Champ mot de passe
    @FXML
    private DatePicker datePicker; // Date de naissance
    @FXML
    private Button signInButton; // Bouton s'inscrire
    @FXML
    private Label label; // Label pour afficher messages
    @FXML
    private Hyperlink connexionLink; // Lien vers page connexion
    @FXML private Hyperlink inscriptionLink; // Lien vers page inscription

    // Redirection vers page connexion
    @FXML
    public void redirectConnexion(ActionEvent e) throws IOException {
        // Charger le FXML login
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/ece/project/login.fxml"));
        Parent root = loader.load();

        // Récupérer le stage actuel
        Stage stage = (Stage) ((javafx.scene.Node) e.getSource()).getScene().getWindow();

        // Remplacer le root de la scène existante
        stage.getScene().setRoot(root);
        stage.setMaximized(true); // plein écran
    }

    //  Redirection vers page inscription (actuelle)
    @FXML
    public void redirectInscription(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/ece/project/signIn.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((javafx.scene.Node) e.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
        stage.setMaximized(true); // plein écran
    }

    //  Service utilisateur pour gérer la création
    private final UserService userService = new UserService();

    // ==== Initialisation ====
    public void initialize() {
        label.setText(""); // vide le label au départ
    }

    // Action du bouton S'inscrire
    @FXML
    public void onSignIn(ActionEvent e) throws IOException {

        // Vérifier que tous les champs sont remplis
        if (nameField.getText().isEmpty() ||
                surnameField.getText().isEmpty() ||
                emailField.getText().isEmpty() ||
                passwordField.getText().isEmpty() ||
                datePicker.getValue() == null) {

            label.setText("Veuillez remplir tous les champs.");
            return; // stop l'exécution si un champ est vide
        }

        // Créer un nouvel utilisateur avec rôle EMPLOYEE
        User newUser = new User(
                nameField.getText(),
                surnameField.getText(),
                emailField.getText(),
                passwordField.getText(),
                "EMPLOYEE"
        );

        // Sauvegarder l'utilisateur en base
        boolean ok = userService.createUser(newUser);

        if (ok) {
            // Afficher un message de succès
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Inscription réussie");
            alert.setHeaderText(null);
            alert.setContentText("Votre compte a été créé avec succès !\nVous allez être redirigé vers la page de connexion.");
            alert.showAndWait();

            // Rediriger vers la page login
            redirectConnexion(e);        } else {
            label.setText("Erreur lors de l'inscription."); // message erreur
        }
    }
}
