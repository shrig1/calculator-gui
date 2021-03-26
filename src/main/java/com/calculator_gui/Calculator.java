package com.calculator_gui;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Calculator extends Application {
    FXMLLoader exprCalcScreenLoader = new FXMLLoader(Calculator.class.getClassLoader().getResource("Expression-Calc.fxml"));
    FXMLLoader eqtnCalcScreenLoader = new FXMLLoader(Calculator.class.getClassLoader().getResource("Equation-Calc.fxml"));
    FXMLLoader aboutScreenLoader = new FXMLLoader(Calculator.class.getClassLoader().getResource("About.fxml"));
    Parent exprParent;
    Parent eqtnParent;
    Parent aboutParent;
    Scene exprScene;
    Scene eqtnScene;
    Scene aboutScene;
    ExprCalcController exprScreenController;
    EqtnCalcController eqtnScreenController;
    AboutController abtScreenController;



    @Override
    public void start(Stage primaryStage) throws Exception{
        initialize();
        primaryStage.setTitle("Calculator");
        primaryStage.setScene(exprScene);
        primaryStage.getIcons().add(new Image(String.valueOf(Calculator.class.getClassLoader().getResource("f(x).jpg"))));
        primaryStage.show();
    }

    public void initialize() throws IOException {
        exprParent = exprCalcScreenLoader.load();
        exprScene = new Scene(exprParent, 671, 554);
        exprScreenController = exprCalcScreenLoader.getController();

        eqtnParent = eqtnCalcScreenLoader.load();
        eqtnScene = new Scene(eqtnParent, 671, 554);
        eqtnScreenController = eqtnCalcScreenLoader.getController();

        aboutParent = aboutScreenLoader.load();
        aboutScene = new Scene(aboutParent, 671, 554);
        abtScreenController = aboutScreenLoader.getController();

        exprScreenController.setEqtnScreen(eqtnScene);
        exprScreenController.setAboutScreen(aboutScene);
        eqtnScreenController.setExprScreen(exprScene);
        eqtnScreenController.setAboutScreen(aboutScene);
        abtScreenController.setExprScreen(exprScene);
        abtScreenController.setEqtnScreen(eqtnScene);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
