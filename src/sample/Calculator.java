package sample;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

import static java.math.BigDecimal.ZERO;

public class Calculator {
    private  Display display;
    private boolean lastButtonWasDigit;
    private String mathOperator = "+";
    private BigDecimal firstBigDecimalMember = ZERO;
    private BigDecimal secondBigDecimalMember = ZERO;


    public Calculator(Display display) {
        this.display = display;
    }

    public BigDecimal getBigDecimalDigit() {
        return new BigDecimal(display.getDisplayDigit().replace(',','.'));
    }

    public void setBigDecimalDigit(BigDecimal bigDecimalDigit) {
        display.setDisplayDigit(bigDecimalDigit.toString());
    }

    public boolean isLastButtonWasDigit() {
        return lastButtonWasDigit;
    }

    public void digit(String digitString) {
        if (lastButtonWasDigit) {
            display.setDisplayDigit(display.getDisplayDigit() + digitString);
        } else {
            display.setDisplayDigit(digitString);
        }
        lastButtonWasDigit = true;
    }

    public void clearingDisplay() {
        setBigDecimalDigit(ZERO);
        lastButtonWasDigit = false;
        mathOperator = "+";
        firstBigDecimalMember = ZERO;
        secondBigDecimalMember = ZERO;
    }

    public void comma() {
        if (!display.getDisplayDigit().contains(".")) {
            display.setDisplayDigit(display.getDisplayDigit() + ".");
        }
        lastButtonWasDigit = true;
    }

    public void mathOperatorMethod(String mathOperatorString) {
        if (lastButtonWasDigit) {
            secondBigDecimalMember = getBigDecimalDigit();
            calculation();
        }
        this.mathOperator = mathOperatorString;
    }

    public void calculationOfTheResult() {
        if (lastButtonWasDigit) {
            secondBigDecimalMember = getBigDecimalDigit();
        }
        calculation();
    }

    public String formatBigDecimal (BigDecimal bigDecimal) {
        return NumberFormat.getInstance().format(bigDecimal);
    }

    private void calculation() {
        switch (mathOperator) {
            case "+":
                setBigDecimalDigit(firstBigDecimalMember.add(secondBigDecimalMember));
                break;
            case "-":
                setBigDecimalDigit(firstBigDecimalMember.subtract(secondBigDecimalMember));
                break;
            case "*":
                setBigDecimalDigit(firstBigDecimalMember.multiply(secondBigDecimalMember));
                break;
            case "/":
                try {
                    BigDecimal result = firstBigDecimalMember.divide(secondBigDecimalMember, 22, RoundingMode.CEILING);
                    setBigDecimalDigit(result.stripTrailingZeros());
                    break;
                } catch (ArithmeticException e) {
                    setBigDecimalDigit(ZERO);
                }
        }
        firstBigDecimalMember = getBigDecimalDigit();
        lastButtonWasDigit = false;
    }
}
