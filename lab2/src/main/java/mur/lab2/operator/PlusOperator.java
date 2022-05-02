package mur.lab2.operator;

import mur.lab2.calculator.*;

public class PlusOperator implements Operator {
    @Override
    public double run(Context context, String[] args) throws OperatorException {
        if(args != null && args.length != 0)
            throw new OperatorIllegalArgumentCountException("operator " +
                    getClass().getCanonicalName() + " must have no arguments");
        double num1, num2;
        try {
            num1 = context.pop();
        }
        catch(ContextEmptyStackException e) {
            throw new OperatorTooFewElementsInStackException(e);
        }
        try {
            num2 = context.pop();
        }
        catch(ContextEmptyStackException e) {
            context.push(num1);
            throw new OperatorTooFewElementsInStackException(e);
        }
        return context.push(num1 + num2);
    }
}
