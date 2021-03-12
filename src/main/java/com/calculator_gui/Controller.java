package com.calculator_gui;

import com.calculator_gui.calculator.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class Controller {
    Environment env = new Environment();
    Lexer lexer;
    Parser parser;
    Evaluator solver;

    @FXML TextField input;
    @FXML Text answer;
    @FXML MenuBar nav;

    @FXML protected void calculation(ActionEvent event) {
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
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }
}
