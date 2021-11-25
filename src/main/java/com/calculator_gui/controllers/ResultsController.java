package com.calculator_gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class ResultsController {
    @FXML Text titleText = new Text();

    @FXML
    public void setTitle(String title) {
        titleText.setText(title);
    }

}
