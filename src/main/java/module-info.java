module fr.ece.project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires javafx.graphics;
    requires javafx.base;


    opens fr.ece.project to javafx.fxml;
    exports fr.ece.project;
    exports fr.ece.project.models;
    exports fr.ece.project.utils;

    opens fr.ece.project.test to javafx.graphics;
    exports fr.ece.project.test;



    opens fr.ece.project.controllers to javafx.fxml;
    exports fr.ece.project.controllers;


}