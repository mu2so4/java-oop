package mur.lab2.operator;

import mur.lab2.calculator.Context;
import mur.lab2.calculator.ContextLabelNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RedefineTest {
    @ParameterizedTest
    @ValueSource(strings = {"name", "plus", "minus", "oop", "opp", "ppp", "OOP", "OPP", "calc"})
    @DisplayName("redefine names")
    void redefine(String name) throws OperatorException, ContextLabelNotFoundException {
        Random rand = new Random();
        Context context = new Context();
        Operator definer = new DefineOperator();
        String[] args = new String[2];
        args[0] = name;
        args[1] = Double.toString(rand.nextDouble());
        definer.run(context, args);

        double num = rand.nextDouble();
        args[1] = Double.toString(num);
        definer.run(context, args);
            assertEquals(num, context.getDouble(name));
    }
}
