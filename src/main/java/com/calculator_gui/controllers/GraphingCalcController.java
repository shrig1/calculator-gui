package com.calculator_gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;

public class GraphingCalcController {
    @FXML
    MenuBar navigation;

    Scene exprScreen;
    Scene eqtnScreen;
    Scene aboutScreen;
    Scene statScreen;

    public void setEqtnScreen(Scene s) { eqtnScreen = s; }
    public void setExprScreen(Scene s) { exprScreen = s; }
    public void setAboutScreen(Scene s) { aboutScreen = s; }
    public void setStatScreen(Scene s) { statScreen = s; }


    @FXML private void openEqtnScreen(ActionEvent e) {
        Stage primaryStage = (Stage)navigation.getScene().getWindow();
        primaryStage.setScene(eqtnScreen);
    }

    @FXML private void openExprScreen(ActionEvent e) {
        Stage primaryStage = (Stage)navigation.getScene().getWindow();
        primaryStage.setScene(exprScreen);
    }

    @FXML private void openAboutScreen(ActionEvent e) {
        Stage primaryStage = (Stage)navigation.getScene().getWindow();
        primaryStage.setScene(aboutScreen);
    }

    @FXML private void openStatScreen(ActionEvent e) {
        Stage primaryStage = (Stage)navigation.getScene().getWindow();
        primaryStage.setScene(statScreen);
    }
}
