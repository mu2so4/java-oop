package mur.lab3.operator;

import mur.lab3.calculator.Context;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class DivideOperatorTest {
    private static final double MAX_RANDOM_NUMBER = 1000;
    private static final int NUMBERS_COUNT = 120;

    @Test
    @DisplayName("Divide one number to other")
    void divide() {
        Operator operator = new DivideOperator();
        Context context = new Context();
        final double[] numbers = new double[NUMBERS_COUNT];
        Random a = new Random();
        for(int index = 0; index < NUMBERS_COUNT; index++) {
            numbers[index] = a.nextDouble() * 2 * MAX_RANDOM_NUMBER - MAX_RANDOM_NUMBER;
            context.push(numbers[index]);
        }
        double trueDiv = numbers[NUMBERS_COUNT - 1];
        for(int index = NUMBERS_COUNT - 2; index >= 0; index--) {
            trueDiv = numbers[index] / trueDiv;
            assertEquals(trueDiv, operator.run(context, null));
        }
    }

    @Test
    @DisplayName("Divide with no numbers on the stack")
    void divideWithNoNumber() {
        Operator operator = new DivideOperator();
        Context context = new Context();
        assertThrows(EmptyStackException.class, () -> operator.run(context, null));
    }

    @Test
    @DisplayName("Divide with one number on the stack")
    void divideWithOneNumber() {
        Operator operator = new DivideOperator();
        Context context = new Context();
        Random rand = new Random();
        double number = rand.nextDouble();
        context.push(number);
        assertThrows(IllegalStateException.class, () -> operator.run(context, null));
        assertEquals(number, context.pop());
    }

    @Test
    @DisplayName("Divide with wrong arguments")
    void divideWithWrongArgs() {
        Operator operator = new DivideOperator();
        Context context = new Context();
        Random rand = new Random();
        context.push(rand.nextDouble());
        context.push(rand.nextDouble());
        context.push(rand.nextDouble());
        String[] arg0 = new String[0];
        assertDoesNotThrow(() -> operator.run(context, arg0));
        String[] args = new String[12];
        assertThrows(IllegalArgumentException.class, () -> operator.run(context, args));
    }

    @Test
    @DisplayName("Divide by zero")
    void divideByZero() {
        Operator operator = new DivideOperator();
        Context context = new Context();
        Random rand = new Random();
        context.push(rand.nextDouble());
        context.push(0);
        assertThrows(ArithmeticException.class, () -> operator.run(context, null));
    }
}
