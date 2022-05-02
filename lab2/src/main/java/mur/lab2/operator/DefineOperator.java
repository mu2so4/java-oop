package mur.lab2.operator;

import mur.lab2.calculator.*;

public class DefineOperator implements Operator {
    @Override
    public double run(Context context, String[] args) throws OperatorException {
        if(args.length != 2)
            throw new OperatorIllegalArgumentCountException("operator " +
                    getClass().getCanonicalName() + " must have 2 arguments");
        double result;
        try {
            result = context.define(args[0], Double.parseDouble(args[1]));
        }
        catch(IllegalLabelException e) {
            throw new OperatorIllegalLabelException(e);
        }
        return result;
    }
}
