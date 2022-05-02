package mur.lab2.operator;

import mur.lab2.calculator.*;

public class SqrtOperator implements Operator {
    @Override
    public double run(Context context, String[] args) throws OperatorException {
        if(args != null && args.length != 0)
            throw new OperatorIllegalArgumentCountException("operator " +
                    getClass().getCanonicalName() + " must have no arguments");
        double num;
        try {
            num = context.pop();
        }
        catch(ContextEmptyStackException e) {
            throw new OperatorTooFewElementsInStackException(e);
        }
        double result = context.push(Math.sqrt(num));
        if(num < 0)
            throw new OperatorNegativeSqrtException();
        return result;
    }
}
