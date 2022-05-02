package mur.lab2.operator;

import mur.lab2.calculator.ContextLabelNotFoundException;

public class OperatorLabelNotFoundException extends OperatorException {
    public OperatorLabelNotFoundException() {
        super();
    }

    public OperatorLabelNotFoundException(Throwable e) {
        super(e);
    }
}
