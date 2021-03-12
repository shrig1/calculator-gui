package com.calculator_gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Calculator extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Calculator.class.getClassLoader().getResource("Expression-Calc.fxml"));
        primaryStage.setTitle("Calculator");
        primaryStage.setScene(new Scene(root, 671, 554));
        primaryStage.show();
        primaryStage.getIcons().add(new Image("U:\\Repos\\calculator-gui\\src\\main\\resources\\icons\\f(x).jpg"));

    }


    public static void main(String[] args) {
        launch(args);
    }
}
