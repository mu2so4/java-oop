package mur.lab2.calculator;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class ContextPoorNamespaceTest {
    @ParameterizedTest
    @ValueSource(strings = {"Plus", "Java", "java", "foo", "bla", "MINUS", "multiply"})
    @DisplayName("define with name lack")
    void defineWithNameLack(String badLabel) throws IllegalLabelException {
        String[] names = {"name", "plus", "minus", "oop", "opp", "ppp", "OOP", "OPP", "calc"};
        Random rand = new Random();
        Context context = new Context();
        for(String name: names) {
            context.define(name, rand.nextDouble());
        }
        for(int index = 0; index < names.length; index++) {
            int sub = index;
            assertDoesNotThrow(() -> context.getDouble(names[sub]));
        }

        assertThrows(ContextLabelNotFoundException.class, () -> context.getDouble(badLabel));
    }
}
