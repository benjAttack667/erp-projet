package fr.ece.project.controllers;

import fr.ece.project.services.AuthService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    // Champs du formulaire FXML
    @FXML private TextField emailField;
    @FXML private TextField passwordField;
    @FXML private Label labelField;
    @FXML private Hyperlink connexionLink;
    @FXML private Hyperlink inscriptionLink;

    // Service qui gère l'authentification
    private final AuthService authService = new AuthService();


    @FXML
    public void initialize() {
        // Vide le texte du label au lancement
        labelField.setText("");
    }


    // Redirection vers login.fxml (page connexion)
    @FXML
    public void redirectConnexion(ActionEvent e) throws IOException {

        // Charge la page FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/ece/project/login.fxml"));
        Parent root = loader.load();

        // Récupère la fenêtre actuelle
        Stage stage = (Stage) ((javafx.scene.Node) e.getSource()).getScene().getWindow();

        // Remplace le contenu de la scène
        stage.getScene().setRoot(root);
        stage.setMaximized(true); // garde la fenêtre maximisée
    }


    // Redirection vers signIn.fxml (page inscription)
    @FXML
    public void redirectInscription(ActionEvent e) throws IOException {

        // Charge le FXML d'inscription
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/ece/project/signIn.fxml"));
        Parent root = loader.load();

        // Récupère la fenêtre actuelle
        Stage stage = (Stage) ((javafx.scene.Node) e.getSource()).getScene().getWindow();

        // Remplace le contenu de la scène
        stage.getScene().setRoot(root);
        stage.setMaximized(true);
    }


    // Quand l'utilisateur clique sur "Connexion"
    @FXML
    public void onLogin(ActionEvent e) throws IOException {

        // Vérifie si les champs sont remplis
        if(emailField.getText().isEmpty() || passwordField.getText().isEmpty()) {
            labelField.setText("Veuillez remplir tous les champs.");
            return;
        }

        // Vérifie les identifiants avec AuthService
        boolean ok = authService.login(emailField.getText(), passwordField.getText());

        if (ok) {
            // Si la connexion est bonne
            labelField.setText("Connexion réussie.");

            // Charge le tableau de bord
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/ece/project/dashboard.fxml"));
            Parent root = loader.load();

            // Récupère la fenêtre actuelle
            Stage stage = (Stage) emailField.getScene().getWindow();

            // Crée une nouvelle scène avec le dashboard
            Scene scene = new Scene(root);

            // Applique la nouvelle scène
            stage.setScene(scene);

            // Force le plein écran
            stage.setMaximized(true);

            stage.show();

        } else {
            // Si identifiants incorrects
            labelField.setText("Vérifiez vos informations.");
        }
    }


    // Méthode utilitaire pour charger facilement une scène en plein écran
    private void loadSceneFullScreen(String fxmlPath, ActionEvent e) throws IOException {

        // Charge le fichier FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Parent root = loader.load();

        // Récupère la fenêtre actuelle
        Stage stage = (Stage) ((javafx.scene.Node) e.getSource()).getScene().getWindow();

        // Crée une scène
        Scene scene = new Scene(root);

        // Applique la scène et met plein écran
        stage.setScene(scene);
        stage.setFullScreen(true);

        stage.show();
    }
}
