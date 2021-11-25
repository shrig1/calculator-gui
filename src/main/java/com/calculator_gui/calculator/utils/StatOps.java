package com.calculator_gui.calculator.utils;

import java.util.ArrayList;
import java.util.List;

public class StatOps {

    public static ArrayList<Double> linRegXY(ArrayList<Double> x_coords, ArrayList<Double> y_coords) {
        if(x_coords.size() != y_coords.size()) throw new Error("x coordinates size != y coordinates size");
        double xMean;
        double xStd;
        double yMean;
        double yStd;
        double r = 0;
        int n = x_coords.size();

        xMean = x_coords.stream().mapToDouble(val -> val).sum() / n;
        yMean = y_coords.stream().mapToDouble(val -> val).sum() / n;

//        System.out.println("XMean: " + xMean + "\nYMean: " + yMean);

        xStd = Math.sqrt(x_coords.stream().mapToDouble(val -> Math.pow(val - xMean, 2)).sum() / n);
        yStd = Math.sqrt(y_coords.stream().mapToDouble(val -> Math.pow(val - yMean, 2)).sum() / n);

//        System.out.println("XStd: " + xStd + "\nYStd: " + yStd);

        r = corrCoeff(x_coords, y_coords, n);
//        System.out.println(r);

        return linRegData(xMean, xStd, yMean, yStd, r);
    }

    public static ArrayList<Double> linRegData(double xMean, double xStd, double yMean, double yStd, double r) {
        double b = r * (yStd / xStd);
        double a = yMean - b * xMean;
        return new ArrayList<>(List.of(a, b));
    }


    public static double corrCoeff(ArrayList<Double> x, ArrayList<Double> y, int n) {
        double xSum = x.stream().mapToDouble(val -> val).sum();
        double ySum = y.stream().mapToDouble(val -> val).sum();
        double xySum = 0;
        double xxSum = 0;
        double yySum = 0;

        for(int i = 0; i < n; i++) {
            xySum += x.get(i) * y.get(i);
            xxSum += x.get(i) * x.get(i);
            yySum += y.get(i) * y.get(i);
        }

        return (n * xySum - xSum * ySum)/
                        (Math.sqrt((n * xxSum -
                        xSum * xSum) * (n * yySum -
                        ySum * ySum)));
    }


}
