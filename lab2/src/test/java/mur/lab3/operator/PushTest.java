package mur.lab3.operator;

import mur.lab3.calculator.Context;
import org.junit.jupiter.api.*;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class PushTest {
    private static final double MAX_RANDOM_NUMBER = 1000;
    private static final int NUMBERS_COUNT = 120;

    @Test
    @DisplayName("push numbers")
    void simplePush() {
        Operator operator = new PushOperator();
        Context context = new Context();
        final double[] numbers = new double[NUMBERS_COUNT];
        final String[] args = new String[1];
        Random rand = new Random();
        for(int index = 0; index < NUMBERS_COUNT; index++) {
            double num = rand.nextDouble() * 2 * MAX_RANDOM_NUMBER - MAX_RANDOM_NUMBER;
            numbers[index] = num;
            args[0] = Double.toString(num);
            operator.run(context, args);
        }
        for(int index = NUMBERS_COUNT - 1; index >= 0; index--) {
            assertEquals(numbers[index], context.pop());
        }
    }

    @Test
    @DisplayName("push with wrong number of args")
    void pushWithWrongArgc() {
        Operator operator = new PushOperator();
        Context context = new Context();
        Random rand = new Random();
        context.push(rand.nextDouble());
        String[] arg0 = new String[0];
        assertThrows(IllegalArgumentException.class, () -> operator.run(context, arg0));
        String[] arg1 = new String[1]; arg1[0] = "12";
        assertDoesNotThrow(() -> operator.run(context, arg1));
        for(int index = 2; index <= 60; index++) {
            String[] args = new String[index];
            assertThrows(IllegalArgumentException.class, () -> operator.run(context, args));
        }
    }

}
