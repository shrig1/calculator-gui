package com.calculator_gui;

import com.calculator_gui.calculator.*;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class Controller {
    Environment env = new Environment();
    Lexer lexer;
    Parser parser;
    Evaluator solver;

    @FXML private TextField input;
    @FXML private Button calculate;
    @FXML private Text answer;
    @FXML private MenuBar navigation;

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
                answer.setText("");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML protected void enterCalculations(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER) {
            calculation();
        }
    }
}
