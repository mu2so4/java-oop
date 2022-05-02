package mur.lab2.operator;

import mur.lab2.calculator.Context;

public interface Operator {
    double run(Context context, String[] args) throws OperatorException;
}
