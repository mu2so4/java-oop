package mur.lab2.operator;

import mur.lab2.calculator.*;

public class PushOperator implements Operator {
    @Override
    public double run(Context context, String[] args) throws OperatorException {
        if(args == null || args.length != 1)
            throw new OperatorIllegalArgumentCountException("operator " +
                    getClass().getCanonicalName() + " must have 1 argument");
        String number = args[0];
        char first = number.charAt(0);
        if(Character.isDigit(first) || first == '-')
            return context.push(Double.parseDouble(number));
        try {
            return context.push(context.getDouble(number));
        }
        catch(ContextLabelNotFoundException e) {
            throw new OperatorLabelNotFoundException(e);
        }
    }
}
