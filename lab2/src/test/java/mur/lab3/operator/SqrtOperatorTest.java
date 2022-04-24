package mur.lab3.operator;

import mur.lab3.calculator.Context;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.EmptyStackException;
import java.util.Random;

public class SqrtOperatorTest {
    private static final double MAX_RANDOM_NUMBER = 1000;
    private static final int NUMBERS_COUNT = 120;

    @Test
    @DisplayName("Sqrt a number")
    void simpleSqrt() {
        Operator operator = new SqrtOperator();
        Context context = new Context();
        final double[] numbers = new double[NUMBERS_COUNT];
        Random rand = new Random();
        for(int index = 0; index < NUMBERS_COUNT; index++) {
            numbers[index] = rand.nextDouble() * MAX_RANDOM_NUMBER;
            context.push(numbers[index]);
        }
        for(int index = NUMBERS_COUNT - 1; index >= 0; index--) {
            assertEquals(Math.sqrt(numbers[index]), operator.run(context, null));
            context.pop();
        }
    }

    @Test
    @DisplayName("Sqrt with no numbers on the stack")
    void sqrtWithNoNumber() {
        Operator operator = new SqrtOperator();
        Context context = new Context();
        assertThrows(EmptyStackException.class, () -> operator.run(context, null));
    }

    @Test
    @DisplayName("Sqrt with wrong arguments")
    void sqrtWithWrongArgs() {
        Operator operator = new SqrtOperator();
        Context context = new Context();
        Random rand = new Random();
        context.push(rand.nextDouble());
        String[] arg0 = new String[0];
        assertDoesNotThrow(() -> operator.run(context, arg0));
        String[] args = new String[12];
        assertThrows(IllegalArgumentException.class, () -> operator.run(context, args));
    }

    @Test
    @DisplayName("Sqrt a negative number")
    void sqrtNegativeNumbers() {
        Operator operator = new SqrtOperator();
        Context context = new Context();
        Random rand = new Random();
        context.push(- rand.nextDouble() * MAX_RANDOM_NUMBER - 1);
        assertThrows(ArithmeticException.class, () -> operator.run(context, null));
    }
}