package fr.ece.project.controllers;

import fr.ece.project.models.User;
import fr.ece.project.services.UserService;
import fr.ece.project.utils.Session;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class UsersController {

    // FX IDs TableView et colonnes
    @FXML private TableView<User> tableUsers; // TableView pour afficher les utilisateurs
    @FXML private TableColumn<User, String> colUserId; // Colonne ID
    @FXML private TableColumn<User, String> colUserName; // Colonne prénom
    @FXML private TableColumn<User, String> colUserSurname; // Colonne nom de famille
    @FXML private TableColumn<User, String> colUserEmail; // Colonne email
    @FXML private TableColumn<User, String> colUserRole; // Colonne rôle

    // FX IDs ComboBox et boutons
    @FXML private ComboBox<String> cbUserRole; // ComboBox pour choisir un rôle
    @FXML private Button btnEditUser; // Bouton modifier utilisateur
    @FXML private Button btnDeleteUser; // Bouton supprimer utilisateur

    private final ObservableList<User> users = FXCollections.observableArrayList(); // liste observable pour TableView
    private final UserService userService = new UserService(); // service pour gérer les utilisateurs

    @FXML
    public void initialize() {

        // Lier les colonnes à User (nom de la propriété)
        colUserId.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("id"));
        colUserName.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("name"));
        colUserSurname.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("surname"));
        colUserEmail.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("email"));
        colUserRole.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("role"));

        // Charger tous les utilisateurs depuis la base
        users.setAll(userService.getAllUsers());
        tableUsers.setItems(users); // mettre la liste dans la TableView

        // Initialiser ComboBox des rôles disponibles
        cbUserRole.setItems(FXCollections.observableArrayList("ADMIN", "MANAGER", "EMPLOYEE"));

        // Vérifier que seul l'admin peut modifier ou supprimer un utilisateur
        if (!"ADMIN".equals(Session.getCurrentUser().getRole())) {
            btnEditUser.setDisable(true); // désactive bouton modifier
            btnDeleteUser.setDisable(true); // désactive bouton supprimer
            cbUserRole.setDisable(true); // désactive ComboBox
        }
    }

    // Méthode appelée lors du clic sur modifier utilisateur
    @FXML
    public void onEditUser() {
        User selected = tableUsers.getSelectionModel().getSelectedItem(); // récupérer utilisateur sélectionné
        if (selected == null) {
            alert("Erreur", "Sélectionne un utilisateur"); // alerte si aucun utilisateur sélectionné
            return;
        }

        String newRole = cbUserRole.getValue(); // récupérer le rôle choisi
        if (newRole == null || newRole.isEmpty()) {
            alert("Erreur", "Sélectionne un rôle"); // alerte si aucun rôle choisi
            return;
        }

        selected.setRole(newRole); // mettre à jour le rôle
        boolean success = userService.updateUser(selected); // mettre à jour en base
        if (success) {
            tableUsers.refresh(); // rafraîchir TableView
            alert("Modifié", "Rôle modifié avec succès pour " + selected.getSurname()); // alerte succès
        } else {
            alert("Erreur", "Impossible de modifier le rôle"); // alerte échec
        }
    }

    // Méthode appelée lors du clic sur supprimer utilisateur
    @FXML
    public void onDeleteUser() {
        User selected = tableUsers.getSelectionModel().getSelectedItem(); // récupérer utilisateur sélectionné
        if (selected == null) {
            alert("Erreur", "Sélectionne un utilisateur"); // alerte si rien sélectionné
            return;
        }

        boolean success = userService.deleteUser(selected.getId()); // supprimer utilisateur en base
        if (success) {
            users.remove(selected); // supprimer de la liste
            alert("Supprimé", "Utilisateur supprimé"); // alerte succès
        } else {
            alert("Erreur", "Impossible de supprimer l'utilisateur"); // alerte échec
        }
    }

    // Méthode utilitaire pour afficher les alertes
    private void alert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION); // type information
        alert.setTitle(title); // titre
        alert.setHeaderText(null); // pas de header
        alert.setContentText(content); // contenu du message
        alert.showAndWait(); // afficher et attendre fermeture
    }

    // Retour au dashboard
    @FXML
    public void goBackDashboard() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/ece/project/dashboard.fxml"));
            Parent root = loader.load();

            // Récupérer la scène actuelle et remplacer la racine
            Stage stage = (Stage) tableUsers.getScene().getWindow();
            stage.getScene().setRoot(root); // remplace root par dashboard
        } catch (IOException e) {
            e.printStackTrace(); // affiche erreur si échec
        }
    }
}
