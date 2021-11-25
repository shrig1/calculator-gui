package com.calculator_gui;

import com.calculator_gui.controllers.*;
import javafx.application.Application;
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
    FXMLLoader graphingScreenLoader = new FXMLLoader(Calculator.class.getClassLoader().getResource("Graphing.fxml"));
    FXMLLoader statsScreenLoader = new FXMLLoader(Calculator.class.getClassLoader().getResource("Statistics.fxml"));
    Parent exprParent;
    Parent eqtnParent;
    Parent aboutParent;
    Parent graphingParent;
    Parent statsParent;
    Scene exprScene;
    Scene eqtnScene;
    Scene aboutScene;
    Scene graphingScene;
    Scene statsScene;
    ExprCalcController exprScreenController;
    EqtnCalcController eqtnScreenController;
    AboutController abtScreenController;
    GraphingCalcController graphScreenController;
    StatCalcController statScreenController;





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

        graphingParent = graphingScreenLoader.load();
        graphingScene = new Scene(graphingParent, 771, 654);
        graphScreenController = graphingScreenLoader.getController();

        statsParent = statsScreenLoader.load();
        statsScene = new Scene(statsParent, 871, 754);
        statScreenController = statsScreenLoader.getController();

        exprScreenController.setEqtnScreen(eqtnScene);
        exprScreenController.setAboutScreen(aboutScene);
        exprScreenController.setGraphScreen(graphingScene);
        exprScreenController.setStatScreen(statsScene);
        eqtnScreenController.setExprScreen(exprScene);
        eqtnScreenController.setAboutScreen(aboutScene);
        eqtnScreenController.setGraphScreen(graphingScene);
        eqtnScreenController.setStatScreen(statsScene);
        abtScreenController.setExprScreen(exprScene);
        abtScreenController.setEqtnScreen(eqtnScene);
        abtScreenController.setGraphScreen(graphingScene);
        abtScreenController.setStatScreen(statsScene);
        graphScreenController.setExprScreen(exprScene);
        graphScreenController.setEqtnScreen(eqtnScene);
        graphScreenController.setAboutScreen(aboutScene);
        graphScreenController.setStatScreen(statsScene);
        statScreenController.setExprScreen(exprScene);
        statScreenController.setEqtnScreen(eqtnScene);
        statScreenController.setGraphScreen(graphingScene);
        statScreenController.setAboutScreen(aboutScene);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
