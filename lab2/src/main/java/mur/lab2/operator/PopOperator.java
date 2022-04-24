package mur.lab3.operator;

import mur.lab3.calculator.Context;

public class PopOperator implements Operator {
    @Override
    public double run(Context context, String[] args) {
        if(args != null && args.length != 0)
            throw new IllegalArgumentException("operator " +
                    getClass().getCanonicalName() + " must have no arguments\n");
        return context.pop();
    }
}
