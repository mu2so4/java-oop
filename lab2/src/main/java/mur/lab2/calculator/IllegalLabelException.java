package mur.lab2.calculator;

public class IllegalLabelException extends ContextException {
    public IllegalLabelException(String badLabel) {
        super(badLabel + " : a label cannot be started with a digit");
    }
}
