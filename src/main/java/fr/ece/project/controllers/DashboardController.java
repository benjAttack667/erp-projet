package fr.ece.project.controllers;

import fr.ece.project.services.SessionManager;
import javafx.fxml.FXML;

public class DashboardController {
    // TODO: ajouter logique pour le tableau de bord

    //Revoie vers login si pas connecté
    @FXML
    public void initialize() {
        if (!SessionManager.isLoggedIn()) {
            // rediriger vers login
        }
    }
}

