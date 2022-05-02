package mur.lab2.operator;

import mur.lab2.calculator.Context;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class PopOperatorPoorStackTest {
    private static final double MAX_RANDOM_NUMBER = 1000;
    private static final int NUMBERS_COUNT = 120;
    private static final int NUMBER_EXCEED_COUNT = 50;

    @Test
    @DisplayName("Pop numbers with lack of numbers")
    void popWithPoorStack() {
        Operator operator = new PopOperator();
        Context context = new Context();
        Random rand = new Random();
        for(int index = 0; index < NUMBERS_COUNT; index++) {
            context.push(rand.nextDouble() * 2 * MAX_RANDOM_NUMBER - MAX_RANDOM_NUMBER);
        }
        for(int index = 0; index < NUMBERS_COUNT; index++) {
            assertDoesNotThrow(() -> operator.run(context, null));
        }
        for(int index = 0; index < NUMBER_EXCEED_COUNT; index++) {
            assertThrows(OperatorTooFewElementsInStackException.class, () -> operator.run(context, null));
        }
    }
}
