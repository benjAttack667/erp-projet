package fr.ece.project.controllers;

import fr.ece.project.models.Project;
import fr.ece.project.models.User;
import fr.ece.project.services.ProjectService;
import fr.ece.project.services.UserService;
import fr.ece.project.utils.Session;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class ProjetsController {

    //  Composants FXML
    @FXML private TableView<Project> tableProjets;
    @FXML private TableColumn<Project, String> colId;
    @FXML private TableColumn<Project, String> colNom;
    @FXML private TableColumn<Project, String> colDesc;
    @FXML private TableColumn<Project, String> colDate;

    @FXML private ComboBox<User> cbAssignEmployee; // Liste déroulante des employés

    @FXML private Button btnAddProjet;
    @FXML private Button btnEditProjet;
    @FXML private Button btnDeleteProjet;
    @FXML private Button btnProfil;

    // Données
    private final ObservableList<Project> projets = FXCollections.observableArrayList();
    private final ProjectService projectService = new ProjectService();
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @FXML
    public void initialize() {

        // Configuration des colonnes du tableau
        colId.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getId()));
        colNom.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getName()));
        colDesc.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getDescription()));
        colDate.setCellValueFactory(cell -> new SimpleStringProperty(
                cell.getValue().getStartDate() != null ? dtf.format(cell.getValue().getStartDate()) : ""
        ));

        // Charger les projets depuis la base
        projets.setAll(projectService.getAllProjects());
        tableProjets.setItems(projets);

        // Charger les employés
        ObservableList<User> employees = FXCollections.observableArrayList(new UserService().getAllUsers());
        employees.removeIf(u -> !"MANAGER".equalsIgnoreCase(u.getRole())); // garder seulement les managers
        cbAssignEmployee.setItems(employees);

        // Comment afficher chaque employé dans la liste (Nom + Prénom)
        cbAssignEmployee.setConverter(new javafx.util.StringConverter<>() {
            @Override
            public String toString(User user) {
                return user != null ? user.getSurname() + " " + user.getName() : "";
            }
            @Override
            public User fromString(String string) { return null; }
        });

        // Vérifier si l'utilisateur est connecté
        if (!Session.isLoggedIn()) {
            alert("Erreur", "Utilisateur non connecté !");
            btnAddProjet.setDisable(true);
            btnEditProjet.setDisable(true);
            btnDeleteProjet.setDisable(true);
            return;
        }

        // Si ce n'est pas un admin → désactiver les boutons
        User current = Session.getCurrentUser();
        boolean isAdmin = "ADMIN".equalsIgnoreCase(current.getRole());
        btnAddProjet.setDisable(!isAdmin);
        btnEditProjet.setDisable(!isAdmin);
        btnDeleteProjet.setDisable(!isAdmin);

       // btnProfil.setVisible("MANAGER".equalsIgnoreCase(current.getRole()));
    }

    // AJOUTER UN PROJET
    @FXML
    public void onAddProjet(ActionEvent e) {
        // Récupérer la fenêtre parente
        Stage ownerStage = (Stage) btnAddProjet.getScene().getWindow();

        // Demander nom du projet
        TextInputDialog dialogName = new TextInputDialog();
        dialogName.initOwner(ownerStage);
        dialogName.setTitle("Ajouter Projet");
        dialogName.setHeaderText("Nom du projet");
        dialogName.setContentText("Nom :");
        Optional<String> nomOpt = dialogName.showAndWait();
        if (nomOpt.isEmpty() || nomOpt.get().isBlank()) return;

        // Demander description
        TextInputDialog dialogDesc = new TextInputDialog();
        dialogDesc.initOwner(ownerStage);
        dialogDesc.setTitle("Ajouter Projet");
        dialogDesc.setHeaderText("Description du projet");
        dialogDesc.setContentText("Description :");
        Optional<String> descOpt = dialogDesc.showAndWait();

        // Vérifier si un employé a été choisi
        User assignedEmployee = cbAssignEmployee.getValue();
        if (assignedEmployee == null) {
            alert("Erreur", "Sélectionne un employé !");
            return;
        }

        // Créer le projet
        Project p = new Project(nomOpt.get(), descOpt.orElse(""), assignedEmployee.getId());

        // Sauvegarder en base
        boolean success = projectService.createProject(p);
        if (success) {
            projets.add(p);
            alert("Succès", "Projet ajouté avec succès !");
        } else {
            alert("Erreur", "Impossible d'ajouter le projet.");
        }
    }

    //MODIFIER UN PROJET
    @FXML
    public void onEditProjet(ActionEvent e) {
        Project selected = tableProjets.getSelectionModel().getSelectedItem();
        if (selected == null) {
            alert("Erreur", "Sélectionne un projet à modifier");
            return;
        }

        // Récupérer la fenêtre parente
        Stage ownerStage = (Stage) btnEditProjet.getScene().getWindow();

        // Modifier nom
        TextInputDialog dialogName = new TextInputDialog(selected.getName());
        dialogName.initOwner(ownerStage); // ← AJOUT ICI
        dialogName.setTitle("Modifier Projet");
        dialogName.setHeaderText("Nom du projet");
        dialogName.setContentText("Nom :");
        Optional<String> nomOpt = dialogName.showAndWait();
        if (nomOpt.isEmpty() || nomOpt.get().isBlank()) return;

        // Modifier description
        TextInputDialog dialogDesc = new TextInputDialog(selected.getDescription());
        dialogDesc.initOwner(ownerStage); // ← AJOUT ICI
        dialogDesc.setTitle("Modifier Projet");
        dialogDesc.setHeaderText("Description du projet");
        dialogDesc.setContentText("Description :");
        Optional<String> descOpt = dialogDesc.showAndWait();

        // Appliquer modifications
        selected.setName(nomOpt.get());
        selected.setDescription(descOpt.orElse(""));

        // Sauvegarder en base
        boolean success = projectService.updateProject(selected);
        if (success) {
            tableProjets.refresh();
            alert("Succès", "Projet modifié avec succès !");
        } else {
            alert("Erreur", "Impossible de modifier le projet.");
        }
    }

    // SUPPRIMER UN PROJET
    @FXML
    public void onDeleteProjet(ActionEvent e) {
        Project selected = tableProjets.getSelectionModel().getSelectedItem();
        if (selected == null) {
            alert("Erreur", "Sélectionne un projet à supprimer");
            return;
        }

        // Supprimer en base
        boolean success = projectService.deleteProject(selected.getId());
        if (success) {
            projets.remove(selected);
            alert("Succès", "Projet supprimé avec succès !");
        } else {
            alert("Erreur", "Impossible de supprimer le projet.");
        }
    }

    // Petite méthode utilitaire pour afficher une alerte
    private void alert(String title, String message) {
        new Alert(Alert.AlertType.INFORMATION, message).show();
    }

    // NAVIGATION ENTRE LES PAGES

    @FXML
    private void goDashboard(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/ece/project/dashboard.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Button) e.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setMaximized(true);
        stage.show();
    }

    @FXML
    private void goTasks(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/ece/project/tasks.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Button) e.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setMaximized(true);
        stage.show();
    }

    @FXML
    private void goProjets(ActionEvent e) {
        alert("Navigation", "Déjà sur Projets");
    }

    @FXML
    private void goProfil(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/ece/project/users.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Button) e.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setMaximized(true);
        stage.show();
    }

    // LOGOUT
    public void logout(ActionEvent e) {
        try {
            Session.clear(); // vider la session

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/ece/project/login.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Button) e.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setMaximized(true);
            stage.show();

        } catch (Exception ex) {
            ex.printStackTrace();
            alert("Erreur", "Impossible de charger la page login !");
        }
    }
}
