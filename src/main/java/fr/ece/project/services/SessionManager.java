package fr.ece.project.services;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SessionManager {

    private static boolean loggedIn = false;

    public static boolean isLoggedIn() {
        return loggedIn;
    }

    public static void login() {
        loggedIn = true;
    }

    public static void logout() {
        loggedIn = false;
    }
}

