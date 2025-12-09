package fr.ece.project.controllers;

import fr.ece.project.models.Task;
import fr.ece.project.services.TaskService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import fr.ece.project.utils.Session;
import fr.ece.project.models.User;

import java.io.IOException;

public class TasksController {

    // FX IDs TableView et colonnes
    @FXML private TableView<Task> tableTasks; // TableView pour afficher les tâches
    @FXML private TableColumn<Task, String> colId; // Colonne ID
    @FXML private TableColumn<Task, String> colTitle; // Colonne titre
    @FXML private TableColumn<Task, String> colDescription; // Colonne description
    @FXML private TableColumn<Task, String> colStatus; // Colonne statut
    @FXML private TableColumn<Task, String> colProjectId; // Colonne projet
    @FXML private TableColumn<Task, String> colAssignedTo; // Colonne assigné à

    // FX IDs boutons
    @FXML private Button btnAdd; // Bouton ajouter
    @FXML private Button btnEdit; // Bouton modifier
    @FXML private Button btnDelete; // Bouton supprimer
    @FXML private Button btnBack; // Bouton retour (Dashboard)
   // @FXML private Button btnProfil;

    private String currentRole; // rôle de l'utilisateur connecté
    private User currentUser; // utilisateur connecté

    private final ObservableList<Task> tasks = FXCollections.observableArrayList(); // liste observable pour TableView
    private final TaskService taskService = new TaskService(); // service pour gérer les tâches

    // Charger toutes les tâches assignées à l'utilisateur courant
    private void loadTasksForCurrentUser() {

        /*if(currentRole.getBytes().equals("MANAGER") || currentRole.getBytes().equals("ADMIN")) {
            tasks.setAll(taskService.getAllTasks()); // si admin, récupérer toutes les tâches
            tableTasks.setItems(tasks);

        }else {
            tasks.setAll(taskService.getByUser(currentUser.getId())); // récupère toutes les tâches de l'utilisateur
            tableTasks.setItems(tasks); // les met dans la TableView
        }*/


            User current = Session.getCurrentUser();

            if (current == null) return;

            switch (current.getRole().toUpperCase()) {

                case "ADMIN":
                case "MANAGER":
                    tasks.setAll(taskService.getAllTasks());
                    break;

                case "EMPLOYEE":
                    tasks.setAll(taskService.getByUser(current.getId()));
                    break;

                default:
                    tasks.clear();
            }

            tableTasks.setItems(tasks);


    }

    @FXML
    public void initialize() {

        // Récupérer l'utilisateur connecté depuis la session
        currentUser = Session.getCurrentUser();
        currentRole = currentUser.getRole();

        System.out.println("Utilisateur connecté = " + currentUser.getId());
        System.out.println("Rôle = " + currentRole);

        // Configurer les colonnes de la TableView
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colProjectId.setCellValueFactory(new PropertyValueFactory<>("projectId"));
        colAssignedTo.setCellValueFactory(new PropertyValueFactory<>("assignedTo"));

        // Charger automatiquement les tâches de l'utilisateur connecté
        loadTasksForCurrentUser();

        // Appliquer les restrictions des boutons selon le rôle de l'utilisateur
        applyRoleRestrictions();
    }

    // Activer/désactiver boutons selon rôle
    private void applyRoleRestrictions() {

        switch (currentRole.toUpperCase()) {

            case "EMPLOYEE":
                // Les employés ne peuvent que voir leurs tâches
                btnAdd.setDisable(true);
                btnEdit.setDisable(true);
                btnDelete.setDisable(true);
                break;

            case "MANAGER":
                // Les managers peuvent ajouter/modifier/supprimer
                btnAdd.setDisable(false);
                btnEdit.setDisable(false);
                btnDelete.setDisable(false);
                // Mais pas gérer les utilisateurs
                //btnProfil.setVisible(true);
                break;

            case "ADMIN":
                // L'admin peut tout faire
                btnAdd.setDisable(false);
                btnEdit.setDisable(false);
                btnDelete.setDisable(false);
                break;
        }

    }

    // ===== Actions des boutons =====

    @FXML
    public void addTask() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/ece/project/AddTask.fxml"));
            Parent root = loader.load();

            // Récupérer le controller du formulaire d'ajout
            AddTaskController controller = loader.getController();

            // Définir callback pour récupérer la nouvelle tâche et l'ajouter à la TableView
            controller.setOnTaskAdded(task -> {
                tasks.add(task); // ajouter à la liste
                // taskService.createTask(task); // inutile si déjà créé dans AddTaskController
            });

            // Ouvrir la fenêtre modale d'ajout
            Stage stage = new Stage();
            stage.setTitle("Ajouter une tâche");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(btnAdd.getScene().getWindow());
            stage.setScene(new Scene(root));
            stage.showAndWait(); // attend que la fenêtre soit fermée

        } catch (IOException e) {
            e.printStackTrace();
            alert("Erreur", "Impossible d'ouvrir le formulaire d'ajout"); // message erreur
        }
    }

    @FXML
    public void editTask() {
        Task selected = tableTasks.getSelectionModel().getSelectedItem(); // récupérer tâche sélectionnée
        if (selected == null) {
            alert("Erreur", "Sélectionne une tâche à modifier"); // alerte si aucune sélection
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/ece/project/EditTask.fxml"));
            Parent root = loader.load();

            EditTaskController controller = loader.getController();
            controller.setTaskToEdit(selected); // envoyer la tâche sélectionnée au formulaire

            // Callback après modification
            controller.setOnTaskUpdated(updatedTask -> {
                int index = tasks.indexOf(selected);
                tasks.set(index, updatedTask); // mettre à jour TableView
            });

            // Ouvrir la fenêtre modale de modification
            Stage stage = new Stage();
            stage.setTitle("Modifier la tâche");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(btnEdit.getScene().getWindow());
            stage.setScene(new Scene(root));
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
            alert("Erreur", "Impossible d’ouvrir la fenêtre de modification");
        }
    }

    @FXML
    public void deleteTask() {
        Task selected = tableTasks.getSelectionModel().getSelectedItem(); // récupérer tâche sélectionnée
        if (selected == null) {
            alert("Erreur", "Sélectionne une tâche à supprimer"); // alerte si rien sélectionné
            return;
        }

        boolean success = taskService.deleteTask(selected.getId()); // supprimer en base
        if (success) {
            tasks.remove(selected); // supprimer de la TableView
            alert("Supprimé", "Tâche supprimée avec succès");
        } else {
            alert("Erreur", "Impossible de supprimer la tâche");
        }
    }

    @FXML
    public void goDashboard(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/ece/project/Dashboard.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow(); // récupérer stage
        Scene scene = new Scene(root); // créer nouvelle scène
        stage.setScene(scene); // remplacer scène
        stage.setMinWidth(1400);
        stage.setMinHeight(800);
        stage.show();
    }

    // ===== Méthode utilitaire pour afficher alertes =====
    private void alert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION); // type information
        alert.setHeaderText(null); // pas de header
        alert.setTitle(title); // titre
        alert.setContentText(message); // message
        alert.showAndWait(); // afficher et attendre fermeture
    }
}
