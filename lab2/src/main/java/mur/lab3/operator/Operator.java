package mur.lab3.operator;

import mur.lab3.calculator.Context;

public interface Operator {
    double run(Context context, String[] args);
}
