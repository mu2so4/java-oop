package mur.lab2.operator;

import mur.lab2.calculator.Context;

public class SkipOperator implements Operator {
    @Override
    public double run(Context context, String[] args) {
        return 0;
    }
}
