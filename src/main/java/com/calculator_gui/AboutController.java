package com.calculator_gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;

public class AboutController {
    @FXML MenuBar navigation;

    Scene exprScreen;
    Scene eqtnScreen;

    public void setEqtnScreen(Scene s) { eqtnScreen = s; }
    public void setExprScreen(Scene s) { exprScreen = s; }


    @FXML private void openEqtnScreen(ActionEvent e) {
        Stage primaryStage = (Stage)navigation.getScene().getWindow();
        primaryStage.setScene(eqtnScreen);
    }

    @FXML private void openExprScreen(ActionEvent e) {
        Stage primaryStage = (Stage)navigation.getScene().getWindow();
        primaryStage.setScene(exprScreen);
    }
}
