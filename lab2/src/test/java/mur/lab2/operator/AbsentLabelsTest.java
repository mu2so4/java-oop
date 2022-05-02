package mur.lab2.operator;

import mur.lab2.calculator.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

public class AbsentLabelsTest {

    @ParameterizedTest
    @ValueSource(strings = {"Plus", "Java", "java", "foo", "bla", "MINUS", "multiply"})
    @DisplayName("define with name lack")
    void defineWithAbsentLabel(String absentLabel) throws OperatorException {
        String[] names = {"name", "plus", "minus", "oop", "opp", "ppp", "OOP", "OPP", "calc"};
        Random rand = new Random();
        Context context = new Context();
        Operator definer = new DefineOperator();
        String[] args = new String[2];
        for(String name: names) {
            args[0] = name;
            args[1] = Double.toString(rand.nextDouble());
            definer.run(context, args);
        }
        for(int index = 0; index < names.length; index++) {
            int sub = index;
            assertDoesNotThrow(() -> context.getDouble(names[sub]));
        }
        assertThrows(ContextLabelNotFoundException.class, () -> context.getDouble(absentLabel));
    }
}
