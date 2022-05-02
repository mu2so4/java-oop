package mur.lab2.calculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class ContextPoorStackTest {
    @Test
    @DisplayName("push and pop numbers")
    void popWithPoorStack() throws ContextEmptyStackException {
        final int elementCount = 500;
        double[] array = new double[elementCount];
        Random rand = new Random();
        Context context = new Context();
        for(int index = 0; index < elementCount; index++) {
            array[index] = rand.nextDouble();
            context.push(array[index]);
        }
        for(int index = elementCount - 1; index >= 0; index--)
            assertDoesNotThrow(context::pop);

        for(int index = 0; index < 100; index++)
            assertThrows(ContextEmptyStackException.class, context::pop);
    }
}
