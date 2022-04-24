package mur.lab3.calculator;

import org.junit.jupiter.api.*;

import java.util.NoSuchElementException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class ContextTest {
    @Test
    @DisplayName("push and pop numbers")
    void pushPop() {
        final int elementCount = 500;
        double[] array = new double[elementCount];
        Random rand = new Random();
        Context context = new Context();
        for(int index = 0; index < elementCount; index++) {
            array[index] = rand.nextDouble();
            context.push(array[index]);
        }
        for(int index = elementCount - 1; index >= 0; index--) {
            assertEquals(array[index], context.pop());
        }
    }

    @Test
    @DisplayName("define with various names")
    void simpleDefine() {
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

    @Test
    @DisplayName("define with name lack")
    void defineWithNameLack() {
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

        String[] falseNames = {"Plus", "Java", "java", "foo", "bla", "MINUS", "multiply"};
        for(String name: falseNames)
            assertThrows(NoSuchElementException.class, () -> context.getDouble(name));
    }

    @Test
    @DisplayName("redefine names")
    void redefine() {
        String[] names = {"name", "plus", "minus", "oop", "opp", "ppp", "OOP", "OPP", "calc"};
        Random rand = new Random();
        Context context = new Context();
        double[] numbers = new double[names.length];
        for(int index = 0; index < names.length; index++) {
            numbers[index] = rand.nextDouble();
            context.define(names[index], numbers[index]);
        }
        for(int index = 0; index < names.length; index++) {
            numbers[index] = rand.nextDouble();
            context.define(names[index], numbers[index]);
        }
        for(int index = 0; index < names.length; index++) {
            assertEquals(numbers[index], context.getDouble(names[index]));
        }
    }

    @Test
    @DisplayName("define with wrong label")
    void defineWithWrongLabel() {
        Context context = new Context();
        Random rand = new Random();
        String[] goodLabels = {"_12oeu", ",bbb", "a1", "_44444444", "label"};
        for(String label: goodLabels) {
            assertDoesNotThrow(() -> context.define(label, rand.nextDouble()));
        }
        String[] badLabels = {"3a", "9eou", "0label"};
        for(String label: badLabels) {
            assertThrows(IllegalArgumentException.class, () -> context.define(label, rand.nextDouble()));
        }
    }
}
