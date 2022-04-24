package mur.lab3.operator;

public class OperatorFactory {
    public static Operator newInstance(String fullClassName)
            throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Object obj = Class.forName(fullClassName).newInstance();
        if(!(obj instanceof Operator))
            throw new ClassCastException(fullClassName +
                    " is not a subclass for " + Operator.class.getCanonicalName());
        return (Operator) obj;
    }

}
