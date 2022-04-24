package mur.lab3.operator;

import mur.lab3.calculator.Context;

public class PushOperator implements Operator {
    @Override
    public double run(Context context, String[] args) {
        if(args == null || args.length != 1)
            throw new IllegalArgumentException("operator " +
                    getClass().getCanonicalName() + " must have 1 argument\n");
        String number = args[0];
        char first = number.charAt(0);
        if(Character.isDigit(first) || first == '-')
            return context.push(Double.parseDouble(number));
        return context.push(context.getDouble(number));
    }
}
