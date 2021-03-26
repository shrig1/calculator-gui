package com.calculator_gui;

import com.calculator_gui.calculator.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ExprCalcController {
    Environment env = new Environment();
    Lexer lexer;
    Parser parser;
    Evaluator solver;

    Scene eqtnScreen;
    Scene aboutScreen;

    @FXML private TextField input;
    @FXML private Button calculate;
    @FXML private Text answer;
    @FXML private MenuBar navigation;
    @FXML TextArea variables_list;

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
//            else {
//                answer.setText(expr);
//            }
        } catch (Exception e) {
//            System.out.println(e.getMessage());
            answer.setText(e.getMessage());
        }
        updateVarsList();
    }

    @FXML protected void enterCalculations(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER) {
            calculation();
        }
    }

    private void updateVarsList() {
        StringBuilder list = new StringBuilder();
        for(Map.Entry<String, Double> var : env.variables.entrySet()) {
            list.append(var.getKey()).append(" = ").append(var.getValue()).append("\n");
        }
        variables_list.setText(list.toString());
    }

    public void setEqtnScreen(Scene s) { eqtnScreen = s; }

    public void setAboutScreen(Scene s) { aboutScreen = s; }

    @FXML private void openEqtnScreen(ActionEvent e) {
        Stage primaryStage = (Stage)navigation.getScene().getWindow();
        primaryStage.setScene(eqtnScreen);
    }

    @FXML private void openAboutScreen(ActionEvent e) {
        Stage primaryStage = (Stage)navigation.getScene().getWindow();
        primaryStage.setScene(aboutScreen);
    }
}
