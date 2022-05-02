package mur.lab2.calculator;

import java.util.*;

public class Context {
    private final HashMap<String, Double> defines = new HashMap<>();
    private final Stack<Double> stack = new Stack<>();

    public double push(double item) {
        return stack.push(item);
    }

    public double pop() throws ContextEmptyStackException {
        try {
            return stack.pop();
        }
        catch(EmptyStackException e) {
            throw new ContextEmptyStackException(e);
        }
    }

    public double define(String label, double value) throws IllegalLabelException {
        if(Character.isDigit(label.charAt(0)))
            throw new IllegalLabelException(label);
        defines.put(label, value);
        return value;
    }

    public double getDouble(String label) throws ContextLabelNotFoundException {
        try {
            return defines.get(label);
        }
        catch(NullPointerException e) {
            throw new ContextLabelNotFoundException(label);
        }
    }
}
