package com.calculator_gui.calculator;

import com.calculator_gui.calculator.utils.MathOps;

import java.util.ArrayList;
import java.util.function.Function;

import static com.calculator_gui.calculator.utils.CheckForCalculationErrors.*;

public class Evaluator implements Expression.Visitor<Double> {
    private Environment env;

    public Evaluator(Environment env) {
        this.env = env;
        env.ret = true;
    }

    public double solve(Expression expr) {
        env.ret = true;
            try{
                double result = evaluate(expr);
                if(env.ret) {
                    if (String.valueOf(result).endsWith(".0")) {
                        env.setPreviousResult(result);
                        return (int) result;
                    } else {
                        env.setPreviousResult(result);
                        return result;
                    }

                }
            } catch(Error e) {
                System.out.println(e.getMessage());
            }
        return 0.0;
    }

    public double evaluate(Expression expr) {
        return expr.accept(this);
    }

    @Override
    public Double visitNullNode(Expression.Null expr) {
        env.ret = false;
        return 0.0;
    }

    @Override
    public Double visitLiteralNode(Expression.Literal expr) { return Double.parseDouble(expr.getValue()); }

    @Override
    public Double visitFunctionNode(Expression.Function expr) {
        if(expr.getState()) {
            double arg = evaluate(expr.getArgument());
            Function<Double, Double> result = MathOps.singleParamFunctions.get(expr.getFunction().getType());
            if(result == null) throw new Error("You used a function that isn't implemented yet");
            return result.apply(arg);
        }
        else {
            ArrayList<Double> args = new ArrayList<>();
            for(Expression arg : expr.getArguments()){
                args.add(evaluate(arg));
            }

            Function<ArrayList<Double>, Double> result = MathOps.multiParamFunctions.get(expr.getFunction().getType());
            if(result == null) throw new Error("You used a function that isn't implemented yet");
            return result.apply(args);
        }
    }

    @Override
    public Double visitUnaryNode(Expression.Unary expr) {
        double sideExpr = evaluate(expr.getSideExpr());

        return switch (expr.getOperator().getType()) {
            case MINUS -> -1.0 * sideExpr;
            case FACTORIAL -> MathOps.factorial(String.valueOf(sideExpr));
            default -> throw new Error("Failure in a Unary Node");
        };


    }


    @Override
    public Double visitGroupingNode(Expression.Grouping expr) {
        if(expr.getType().equals("grouping")) {
            return evaluate(expr.getExpression());
        }
        else if(expr.getType().equals("abs")) {
            return Math.abs(evaluate(expr.getExpression()));
        }

        //Unreachable
        throw new Error("Failure in a Grouping Node");
    }


    @Override
    public Double visitBinaryNode(Expression.Binary expr) {
        double left = evaluate(expr.getLeft());
        double right = evaluate(expr.getRight());
        checkArithmeticErrors(expr.getOperator(), left, right);
        switch (expr.getOperator().getType()) {
            case MINUS:
                return left - right;
            case PLUS:
                return left + right;
            case SLASH:
                return left / right;
            case STAR:
                return left * right;
            case EXP:
                return Math.pow(left, right);
            case MODULO:
                return left % right;
        }
        // Unreachable.
        throw new Error("Failure in a Binary Node");
    }

    @Override
    public Double visitAssignNode(Expression.Assign expr) {
        env.variables.put(expr.getName(), evaluate(expr.getExpression()));
        env.ret = false;
        return 0.0;
    }
}