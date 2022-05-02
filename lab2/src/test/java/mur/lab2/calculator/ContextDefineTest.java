package mur.lab2.calculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContextDefineTest {
    @Test
    @DisplayName("define with various names")
    void define() throws IllegalLabelException, ContextLabelNotFoundException {
        String[] names = {"name", "plus", "minus", "oop", "opp", "ppp", "OOP", "OPP", "calc"};
        Random rand = new Random();
        Context context = new Context();
        double[] numbers = new double[names.length];
        for(int index = 0; index < names.length; index++) {
            numbers[index] = rand.nextDouble();
            context.define(names[index], numbers[index]);
        }
        for(int index = 0; index < names.length; index++) {
            assertEquals(numbers[index], context.getDouble(names[index]));
        }
    }
}
