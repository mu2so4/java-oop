package mur.lab3.operator;

import mur.lab3.calculator.Context;

public class DefineOperator implements Operator {
    @Override
    public double run(Context context, String[] args) {
        if(args.length != 2)
            throw new IllegalArgumentException("operator " +
                    getClass().getCanonicalName() + " must have 2 arguments\n");
        return context.define(args[0], Double.parseDouble(args[1]));
    }
}
