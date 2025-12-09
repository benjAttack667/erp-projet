package fr.ece.project.controllers;

import fr.ece.project.models.User;
import fr.ece.project.utils.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

public class DashboardController {

    // Références aux boutons dans le fichier FXML
    @FXML private Button btnDashboard;
    @FXML private Button btnTasks;
    @FXML private Button btnProjets;
    @FXML private Button btnProfil;
    @FXML private Button btnLogout;

    // Stocke l'utilisateur actuellement connecté
    private User loggedUser;

    @FXML
    public void initialize() {
        // Récupérer l'utilisateur connecté depuis la session
        loggedUser = Session.getCurrentUser();

        // Vérifier que quelqu'un est connecté
        if (loggedUser == null) {
            System.out.println("Aucun utilisateur connecté");
            return;
        }

        // Récupérer le rôle de l'utilisateur (ADMIN, MANAGER, USER)
        String role = loggedUser.getRole();

        // Si ce n'est pas ADMIN ou MANAGER, on cache le bouton "Projets"
        btnProjets.setVisible(role.equalsIgnoreCase("ADMIN") || role.equalsIgnoreCase("MANAGER"));


        // Si c'est un ADMIN, le bouton Profil devient "Gestion users"
        if (role.equalsIgnoreCase("ADMIN")||role.equalsIgnoreCase("MANAGER")) {
            btnProfil.setText("Gestion users");
        } else {
            // Si ce n'est pas un admin, le bouton Profil disparaît
            btnProfil.setVisible(false);
        }
    }

    // Charge la vue Dashboard
    @FXML
    public void goDashboard(ActionEvent e) throws IOException {
        loadScene("/fr/ece/project/dashboard.fxml", e);
    }

    // Charge la vue des tâches
    @FXML
    public void goTasks(ActionEvent e) throws IOException {
        loadScene("/fr/ece/project/tasks.fxml", e);
    }

    // Charge la vue des projets
    @FXML
    public void goProjets(ActionEvent e) throws IOException {
        loadScene("/fr/ece/project/projets.fxml", e);
    }

    // Charge la vue de gestion des utilisateurs
    @FXML
    public void goProfil(ActionEvent e) throws IOException {
        loadScene("/fr/ece/project/users.fxml", e);
    }

    // Déconnexion de l'utilisateur
    @FXML
    public void logout(ActionEvent e) throws IOException {
        // Supprime les informations en session
        Session.clear();
        // Retourne sur la page de connexion
        loadScene("/fr/ece/project/login.fxml", e);
    }

    // Méthode réutilisable pour changer de page
    private void loadScene(String fxmlPath, ActionEvent e) throws IOException {
        // Charger le fichier FXML demandé
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Parent root = loader.load();

        // Récupérer la fenêtre actuelle
        Stage stage = (Stage) ((Button)e.getSource()).getScene().getWindow();

        // Créer une nouvelle scène avec dimensions de base
        Scene scene = new Scene(root, 1200, 800);

        // Appliquer la scène à la fenêtre
        stage.setScene(scene);

        // Mettre en plein écran
        stage.setMaximized(true);

        // Afficher la fenêtre
        stage.show();
    }
}
