package mur.lab3.operator;

import mur.lab3.calculator.Context;

public class SqrtOperator implements Operator {
    @Override
    public double run(Context context, String[] args) {
        if(args != null && args.length != 0)
            throw new IllegalArgumentException("operator " +
                    getClass().getCanonicalName() + " must have no arguments\n");
        double num = context.pop();
        double result = context.push(Math.sqrt(num));
        if(num < 0)
            throw new ArithmeticException("sqrt of negative number");
        return result;
    }
}
