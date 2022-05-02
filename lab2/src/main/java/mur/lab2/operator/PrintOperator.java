package mur.lab2.operator;

import mur.lab2.calculator.*;

public class PrintOperator implements Operator {
    @Override
    public double run(Context context, String[] args) throws OperatorException {
        if(args != null && args.length != 0)
            throw new OperatorIllegalArgumentCountException("operator " +
                    getClass().getCanonicalName() + " must have no arguments");
        double last;
        try {
            last = context.pop();
        }
        catch(ContextEmptyStackException e) {
            throw new OperatorTooFewElementsInStackException(e);
        }
        System.out.println(last);
        return context.push(last);
    }
}
