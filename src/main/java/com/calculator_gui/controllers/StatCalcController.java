package com.calculator_gui.controllers;

import com.calculator_gui.Calculator;
import com.calculator_gui.calculator.utils.StatOps;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class StatCalcController {
    Scene exprScreen;
    Scene eqtnScreen;
    Scene aboutScreen;
    Scene graphScreen;

    @FXML MenuBar navigation;
    @FXML TextField LinRegXCoords;
    @FXML TextField LinRegYCoords;
    @FXML TextField LinRegTitle;
    @FXML TextField LinRegXMean;
    @FXML TextField LinRegYMean;
    @FXML TextField LinRegXSTD;
    @FXML TextField LinRegYSTD;
    @FXML TextField LinRegRCoeff;
    @FXML Button LinRegXYBtn;
    @FXML Button LinRegMeSdBtn;
    FXMLLoader resultsLoader = new FXMLLoader(Calculator.class.getClassLoader().getResource("ResultScrnTemplate.fxml"));
    Stage results = new Stage();
    ResultsController cont = new ResultsController();


    public StatCalcController() throws IOException {
        results.setTitle("Results");
        results.setScene(new Scene(resultsLoader.load(), 671, 554));
    }


    @FXML
    public void linRegData() {
        double xMean = Double.parseDouble(LinRegXMean.getText());
        double yMean = Double.parseDouble(LinRegYMean.getText());
        double xStd = Double.parseDouble(LinRegXSTD.getText());
        double yStd = Double.parseDouble(LinRegYSTD.getText());
        double r = Double.parseDouble(LinRegRCoeff.getText());

        ArrayList<Double> coefficients = StatOps.linRegData(xMean, xStd, yMean, yStd, r);
        System.out.printf("The LSRL will be: y_hat = %.3f + %.3fx\n", coefficients.get(0), coefficients.get(1));
    }

    @FXML
    public void linRegXY(ActionEvent e) throws IOException {
        ArrayList<Double> x_coords = new ArrayList<>();
        ArrayList<Double> y_coords = new ArrayList<>();

        String[] x = LinRegXCoords.getText().replaceAll(" ", "").split(",");
        String[] y = LinRegYCoords.getText().replaceAll(" ", "").split(",");
        for(String c : x) {
            x_coords.add(Double.parseDouble(c));
        }
        for(String c : y) {
            y_coords.add(Double.parseDouble(c));
        }

        ArrayList<Double> coefficients = StatOps.linRegXY(x_coords, y_coords);
        System.out.printf("The LSRL will be: %.3f + %.3fx\n", coefficients.get(0), coefficients.get(1));

        cont.setTitle(LinRegTitle.getText());
        results.show();
    }



    //------- Navigation shit ----------
    public void setExprScreen(Scene s) { exprScreen = s; }

    public void setEqtnScreen(Scene s) { eqtnScreen = s; }

    public void setAboutScreen(Scene s) { aboutScreen = s; }

    public void setGraphScreen(Scene s) { graphScreen = s; }

    @FXML
    private void openExprScreen(ActionEvent e) {
        Stage primaryStage = (Stage)navigation.getScene().getWindow();
        primaryStage.setScene(eqtnScreen);
    }

    @FXML
    private void openEqtnScreen(ActionEvent e) {
        Stage primaryStage = (Stage)navigation.getScene().getWindow();
        primaryStage.setScene(eqtnScreen);
    }

    @FXML private void openAboutScreen(ActionEvent e) {
        Stage primaryStage = (Stage)navigation.getScene().getWindow();
        primaryStage.setScene(aboutScreen);
    }

    @FXML private void openGraphScreen(ActionEvent e) {
        Stage primaryStage = (Stage)navigation.getScene().getWindow();
        primaryStage.setScene(graphScreen);
    }
}
