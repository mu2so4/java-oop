package mur.lab2.operator;

import mur.lab2.calculator.*;

public class PopOperator implements Operator {
    @Override
    public double run(Context context, String[] args) throws OperatorException {
        if(args != null && args.length != 0)
            throw new OperatorIllegalArgumentCountException("operator " +
                    getClass().getCanonicalName() + " must have no arguments");
        try {
            return context.pop();
        }
        catch(ContextEmptyStackException e) {
            throw new OperatorTooFewElementsInStackException(e);
        }
    }
}
