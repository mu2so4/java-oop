package mur.lab3.operator;

import mur.lab3.calculator.Context;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class PrintOperatorTest {
    private static final double MAX_RANDOM_NUMBER = 1000;
    private static final int NUMBERS_COUNT = 120;

    @Test
    @DisplayName("Simple Print")
    void simplePrint() {
        Operator operator = new PrintOperator();
        Context context = new Context();
        final double[] numbers = new double[NUMBERS_COUNT];
        Random a = new Random();
        for(int index = 0; index < NUMBERS_COUNT; index++) {
            numbers[index] = a.nextDouble() * 2 * MAX_RANDOM_NUMBER - MAX_RANDOM_NUMBER;
            context.push(numbers[index]);
        }

        for(int index = NUMBERS_COUNT - 1; index >= 0; index--) {
            operator.run(context, null);
            assertEquals(numbers[index], context.pop());
        }
    }

    @Test
    @DisplayName("Print with wrong arguments")
    void printWithWrongArgs() {
        Operator operator = new PrintOperator();
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
}
