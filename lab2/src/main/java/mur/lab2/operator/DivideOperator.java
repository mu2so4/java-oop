package mur.lab3.operator;

import mur.lab3.calculator.Context;

import java.util.EmptyStackException;

public class DivideOperator implements Operator {
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
            throw new IllegalStateException("division with one operator on the stack");
        }
        double result = context.push(num2 / num1);
        if(num1 == 0)
            throw new ArithmeticException("division by zero");
        return result;
    }
}
