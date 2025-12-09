package fr.ece.project.controllers;

import fr.ece.project.models.Task;
import fr.ece.project.services.TaskService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.function.Consumer;

public class EditTaskController {

    // Champs du formulaire FXML
    @FXML private TextField txtTitle;
    @FXML private TextArea txtDescription;
    @FXML private TextField txtStatus;
    @FXML private TextField txtProjectId;
    @FXML private TextField txtAssignedTo;

    // La tâche que l’on veut modifier
    private Task taskToEdit;

    // Fonction callback pour actualiser la liste dans TasksController
    private Consumer<Task> onTaskUpdated;

    // Service gérant les opérations sur les tâches
    private final TaskService taskService = new TaskService();


    // Méthode appelée par TasksController pour envoyer la tâche à modifier
    public void setTaskToEdit(Task task) {
        this.taskToEdit = task;

        // Pré-remplir le formulaire avec les données existantes
        txtTitle.setText(task.getTitle());
        txtDescription.setText(task.getDescription());
        txtStatus.setText(task.getStatus());
        txtProjectId.setText(task.getProjectId());
        txtAssignedTo.setText(task.getAssignedTo());
    }

    // Permet à TasksController de fournir une fonction callback
    public void setOnTaskUpdated(Consumer<Task> callback) {
        this.onTaskUpdated = callback;
    }


    @FXML
    public void save() throws IOException {

        // Met à jour les valeurs de la tâche
        taskToEdit.setTitle(txtTitle.getText());
        taskToEdit.setDescription(txtDescription.getText());
        taskToEdit.setStatus(txtStatus.getText());
        taskToEdit.setProjectId(txtProjectId.getText());
        taskToEdit.setAssignedTo(txtAssignedTo.getText());

        // Enregistre les modifications dans la base de données
        boolean ok = taskService.updateTask(taskToEdit);

        if (!ok) {
            showMessage("Erreur", "Impossible de modifier la tâche");
            return;
        }

        // Informe TasksController qu’une tâche a été modifiée
        if (onTaskUpdated != null) {
            onTaskUpdated.accept(taskToEdit);
        }

        // Message de succès
        showMessage("Succès", "Tâche modifiée avec succès");

        // Ferme la fenêtre actuelle (popup d’édition)
        Stage currentStage = (Stage) txtTitle.getScene().getWindow();
        currentStage.close();

        // Recharge la page Tasks.fxml dans une nouvelle fenêtre
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/ece/project/Tasks.fxml"));
        Parent root = loader.load();

        Stage newStage = new Stage();
        newStage.setTitle("Liste des tâches");
        newStage.setScene(new Scene(root));
        newStage.setFullScreen(true);
        newStage.show();
    }


    // Petite méthode utilitaire pour afficher une alerte
    private void showMessage(String title, String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(title);
        a.setHeaderText(null);
        a.setContentText(msg);
        a.showAndWait();
    }
}
