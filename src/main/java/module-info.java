module calculator.gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    exports com.calculator_gui;
    exports com.calculator_gui.controllers;
    opens com.calculator_gui;
    opens com.calculator_gui.controllers;
}