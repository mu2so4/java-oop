package mur.lab3.calculator;

import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Stack;

public class Context {
    private final HashMap<String, Double> defines = new HashMap<>();
    private final Stack<Double> stack = new Stack<>();

    public double push(double item) {
        return stack.push(item);
    }

    public double pop() {
        return stack.pop();
    }

    public double define(String label, double value) {
        if(Character.isDigit(label.charAt(0)))
            throw new IllegalArgumentException("label " + label + " cannot start with a digit");
        defines.put(label, value);
        return value;
    }

    public double getDouble(String label) {
        if(!defines.containsKey(label))
            throw new NoSuchElementException("label \"" + label + "\" not found\n");
        return defines.get(label);
    }
}
