package mur.lab3.operator;

import mur.lab3.calculator.Context;

import java.util.EmptyStackException;

public class PlusOperator implements Operator {
    @Override
    public double run(Context context, String[] args) {
        if(args != null && args.length != 0)
            throw new IllegalArgumentException("operator " +
                    getClass().getCanonicalName() + " must have no arguments\n");
        double num1, num2;
        num1 = context.pop();
        try {
            num2 = context.pop();
        }
        catch(EmptyStackException e) {
            context.push(num1);
            throw new IllegalStateException("plus with one operator on the stack");
        }
        return context.push(num1 + num2);
    }
}
