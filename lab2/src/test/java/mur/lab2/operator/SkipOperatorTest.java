package mur.lab3.operator;

import mur.lab3.calculator.Context;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class SkipOperatorTest {
    private static final double MAX_RANDOM_NUMBER = 1000;
    private static final int NUMBERS_COUNT = 120;

    @Test
    @DisplayName("simple comment")
    void comment() {
        Operator operator = new SkipOperator();
        Context context = new Context();
        Random rand = new Random();
        double[] data = new double[NUMBERS_COUNT];
        for(int index = 0; index < NUMBERS_COUNT; index++) {
            data[index] = rand.nextDouble() * MAX_RANDOM_NUMBER * 2 - MAX_RANDOM_NUMBER;
            context.push(data[index]);
        }
        operator.run(context, null);
        for(int index = 0; index <= 1000; index++) {
            String[] args = new String[index];
            operator.run(context, args);
        }
        for(int index = NUMBERS_COUNT - 1; index >= 0; index--) {
            assertEquals(data[index], context.pop());
        }
    }
}
