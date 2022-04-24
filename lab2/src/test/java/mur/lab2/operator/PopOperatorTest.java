package mur.lab3.operator;

import mur.lab3.calculator.Context;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class PopOperatorTest {
    private static final double MAX_RANDOM_NUMBER = 1000;
    private static final int NUMBERS_COUNT = 120;
    private static final int NUMBER_EXCEED_COUNT = 50;

    @Test
    @DisplayName("Pop numbers")
    void simplePop() {
        Operator popper = new PopOperator();
        Context context = new Context();
        final double[] numbers = new double[NUMBERS_COUNT];
        Random rand = new Random();
        for(int index = 0; index < NUMBERS_COUNT; index++) {
            numbers[index]= rand.nextDouble() * 2 * MAX_RANDOM_NUMBER - MAX_RANDOM_NUMBER;
            context.push(numbers[index]);
        }
        for(int index = NUMBERS_COUNT - 1; index >= 0; index--) {
            assertEquals(numbers[index], popper.run(context, null));
        }
    }

    @Test
    @DisplayName("Pop numbers with lack of numbers")
    void popWithNumberLack() {
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
            assertThrows(EmptyStackException.class, () -> operator.run(context, null));
        }
    }

    @Test
    @DisplayName("pop with wrong number of args")
    void popWithWrongArgc() {
        Operator operator = new PopOperator();
        Context context = new Context();
        Random rand = new Random();
        context.push(rand.nextDouble());
        String[] arg0 = new String[0];
        assertDoesNotThrow(() -> operator.run(context, arg0));
        for(int index = 1; index <= 60; index++) {
            String[] args = new String[index];
            assertThrows(IllegalArgumentException.class, () -> operator.run(context, args));
        }
    }
}
