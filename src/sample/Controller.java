package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller implements Display {

    @FXML
    private TextField display;
    private Calculator calculator;

    public Controller() {
        calculator = new Calculator(this);
    }


    @FXML
    void buttonDigitClick(ActionEvent event) {
        Button button = (Button)event.getSource();
        String digitString = button.getText();
        calculator.digit(digitString);
    }

    @FXML
    void buttonMathOperatorClick(ActionEvent event) {
        Button button = (Button)event.getSource();
        String mathOperatorString = button.getText();
        calculator.mathOperatorMethod(mathOperatorString);
    }

    @FXML
    void buttonEquallyClick(ActionEvent event) {
        calculator.calculationOfTheResult();
    }

    @FXML
    void buttonCommaClick(ActionEvent event) {
        calculator.comma();
    }

    @FXML
    void buttonClearClick(ActionEvent event) {
        calculator.clearingDisplay();
    }

    @Override
    public String getDisplayDigit() {
        return display.getText();
    }

    @Override
    public void setDisplayDigit(String displayDigit) {
        display.setText(displayDigit);
    }
}
