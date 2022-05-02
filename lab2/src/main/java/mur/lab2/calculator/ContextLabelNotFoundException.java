package mur.lab2.calculator;

public class ContextLabelNotFoundException extends ContextException {
    public ContextLabelNotFoundException(String badLabel) {
        super(badLabel + " not found");
    }
}
