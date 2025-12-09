package fr.ece.project.controllers;

import fr.ece.project.models.Employer;
import fr.ece.project.models.Task;
import fr.ece.project.models.Project;
import fr.ece.project.models.User;
import fr.ece.project.services.TaskService;
import fr.ece.project.services.ProjectService;
import fr.ece.project.services.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.function.Consumer;

public class AddTaskController {

    // Champs liés aux éléments FXML
    @FXML private TextField tfTitle;
    @FXML private TextArea taDescription;
    @FXML private ComboBox<String> cbStatus;
    @FXML private DatePicker dpDueDate;
    @FXML private ComboBox<String> cbProjects; // Liste déroulante des projets
    @FXML private Button btnSave;
    @FXML private Button btnCancel;
    @FXML private ComboBox<String> cbEmployee;

    // Services permettant d'accéder aux données
    private final TaskService taskService = new TaskService();
    private final ProjectService projectService = new ProjectService();
    private final UserService userService = new UserService();



    @FXML
    public void initialize() {
        // Remplir les statuts dans la liste
        cbStatus.getItems().addAll("TODO", "IN PROGRESS", "DONE");
        cbStatus.getSelectionModel().selectFirst(); // sélection par défaut

        // Charger les projets depuis ProjectService
        cbProjects.getItems().clear();
        for (Project p : projectService.getAllProjects()) {
            // Ajout sous forme "Nom - ID"
            cbProjects.getItems().add(p.getName() + " - " + p.getId());
        }

        // Charger les employés depuis UserService
        cbEmployee.getItems().clear();
        for (User u : userService.getAllUsers()) {
            if ("EMPLOYEE".equals(u.getRole())) {
                // Ajout sous forme "Nom - ID"
                cbEmployee.getItems().add(u.getName() + u.getSurname() + " - " + u.getId());
            }
        }
    }

    // Callback utilisé pour rafraîchir la liste dans TasksController
    private Consumer<Task> onTaskAdded;

    public void setOnTaskAdded(Consumer<Task> callback) {
        this.onTaskAdded = callback;
    }

    // Action du bouton "Enregistrer"
    @FXML
    public void saveTask() {

        // Vérification du titre
        if (tfTitle.getText().isEmpty()) {
            alert("Erreur", "Le titre est obligatoire !");
            return;
        }

        // Vérification qu’un projet est bien sélectionné
        if (cbProjects.getValue() == null) {
            alert("Erreur", "Veuillez sélectionner un projet !");
            return;
        }

        // Vérification qu’un employé est bien sélectionné
        if (cbEmployee.getValue() == null) {
            alert("Erreur", "Veuillez sélectionner un employé !");
            return;
        }

        // Récupération du projet sélectionné
        String selectedProject = cbProjects.getValue();
        String projectId = selectedProject.split(" - ")[0]; // extraire l'ID

        // Récupération de l'employé sélectionné
        String selectedEmployee = cbEmployee.getValue();
        String employeeId = selectedEmployee.split(" - ")[0]; // extraire l'ID

        // Création de la nouvelle tâche
        Task task = new Task();
        task.setTitle(tfTitle.getText());
        task.setDescription(taDescription.getText());
        task.setStatus(cbStatus.getValue());
        task.setDueDate(dpDueDate.getValue());
        task.setProjectId(projectId);
        task.setAssignedTo(employeeId);

        // Sauvegarde via TaskService
        boolean success = taskService.createTask(task);
        if (!success) {
            alert("Erreur", "Impossible d'ajouter la tâche.");
            return;
        }

        // Appeler le callback pour mettre à jour la liste des tâches
        if (onTaskAdded != null)
            onTaskAdded.accept(task);

        alert("Succès", "Tâche ajoutée !");

        // Fermer la fenêtre actuelle
        Stage currentStage = (Stage) tfTitle.getScene().getWindow();
        currentStage.close();

        try {
            // Charger la page Tasks.fxml dans une nouvelle fenêtre
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/ece/project/Tasks.fxml"));
            Parent root = loader.load();

            Stage newStage = new Stage();
            newStage.setTitle("Liste des tâches");
            newStage.setScene(new Scene(root));
            newStage.setFullScreen(true); // Plein écran
            newStage.show();

        } catch (IOException e) {
            e.printStackTrace();
            alert("Erreur", "Impossible d’ouvrir la page des tâches");
        }
    }

    // Action du bouton "Annuler"
    @FXML
    public void cancel() {
        closeWindow();
    }

    // Fermer la fenêtre courante
    private void closeWindow() {
        Stage stage = (Stage) tfTitle.getScene().getWindow();
        stage.close();
    }

    // Affichage d’un message
    private void alert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
