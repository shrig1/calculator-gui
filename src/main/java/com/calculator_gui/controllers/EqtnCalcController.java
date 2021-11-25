package com.calculator_gui.controllers;

import com.calculator_gui.calculator.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

// Right now there is nothing different from this screen and the ExprCalc screen, except there is no Real Time Variables Viewer
public class EqtnCalcController {
    Environment env = new Environment();
    Lexer lexer;
    Parser parser;
    Evaluator solver;

    Scene exprScreen;
    Scene aboutScreen;
    Scene graphScreen;
    Scene statScreen;

    @FXML private TextField input;
    @FXML private Button calculate;
    @FXML private Text answer;
    @FXML private MenuBar navigation;
    @FXML Text variables_list;

    @FXML protected void calculation() {
        String expr = input.getText();
        try{
//                System.out.println(line);
            lexer = new Lexer(expr);
            ArrayList<Token> tokens = lexer.scanTokens();

//                System.out.println(tokens);
            parser = new Parser(tokens, env);
            Expression expression = parser.parse();
//                System.out.println(new AstPrinter().print(expression));
            solver = new Evaluator(env);
            double value = solver.solve(expression);
            if(env.ret) {
                answer.setText(String.valueOf(value));
            }
            else {
                answer.setText(expr);
            }
        } catch (Exception e) {
//            System.out.println(e.getMessage());
            answer.setText("Not a valid expression :(");
        }
    }

    @FXML protected void enterCalculations(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER) {
            calculation();
        }
    }

    //------- Navigation shit ----------
    public void setExprScreen(Scene s) { exprScreen = s; }
    public void setAboutScreen(Scene s) { aboutScreen = s; }
    public void setGraphScreen(Scene s) { graphScreen = s; }
    public void setStatScreen(Scene s) { statScreen = s; }

    @FXML private void openExprScreen(ActionEvent e) {
        Stage primaryStage = (Stage)navigation.getScene().getWindow();
        primaryStage.setScene(exprScreen);
    }

    @FXML private void openAboutScreen(ActionEvent e) {
        Stage primaryStage = (Stage)navigation.getScene().getWindow();
        primaryStage.setScene(aboutScreen);
    }

    @FXML private void openGraphScreen(ActionEvent e) {
        Stage primaryStage = (Stage)navigation.getScene().getWindow();
        primaryStage.setScene(graphScreen);
    }

    @FXML private void openStatScreen(ActionEvent e) {
        Stage primaryStage = (Stage)navigation.getScene().getWindow();
        primaryStage.setScene(statScreen);
    }
}