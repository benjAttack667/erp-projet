package fr.ece.project.utils;

import fr.ece.project.models.User;

public final class Session {
    private Session() {}

    private static volatile User currentUser;

    public static void setCurrentUser(User u) { currentUser = u; }
    public static User getCurrentUser() { return currentUser; }
    public static void clear() { currentUser = null; }
    public static boolean isLoggedIn() { return currentUser != null; }
}

