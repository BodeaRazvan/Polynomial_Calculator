module org.PolynomialCalculator {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.PolynomialCalculator.controller to javafx.fxml;
    exports org.PolynomialCalculator;
    exports org.PolynomialCalculator.model;
    exports org.PolynomialCalculator.controller;
}

