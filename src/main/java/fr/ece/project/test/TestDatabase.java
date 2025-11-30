package fr.ece.project.test;

import fr.ece.project.utils.Database;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;

public class TestDatabase extends Application {
    private Label statusLabel = new Label("Vérification de la connexion...");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        statusLabel.setWrapText(true);
        Button retry = new Button("Tester la connexion");
        retry.setOnAction(e -> checkConnection());

        VBox root = new VBox(10, statusLabel, retry);
        root.setPadding(new Insets(15));

        Scene scene = new Scene(root, 300, 120);
        stage.setTitle("Test de la base de données");
        stage.setScene(scene);
        stage.show();

        checkConnection();
    }

    private void checkConnection() {
        try (Connection con = Database.getConnection()) {
            if (con != null && !con.isClosed()) {
                statusLabel.setText("✅ Connexion réussie !");
            } else {
                statusLabel.setText("❌ Connexion échouée !");
            }
        } catch (Exception ex) {
            statusLabel.setText("❌ Connexion échouée : " + ex.getMessage());
        }
    }
}
