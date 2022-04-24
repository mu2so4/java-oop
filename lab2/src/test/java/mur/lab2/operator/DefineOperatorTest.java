package mur.lab3.operator;

import mur.lab3.calculator.Context;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;
import java.util.Random;

public class DefineOperatorTest {
    @Test
    @DisplayName("define with the same names")
    void simpleDefine() {
        String[] names = {"name", "plus", "minus", "oop", "opp", "ppp", "OOP", "OPP", "calc"};
        Random rand = new Random();
        Context context = new Context();
        Operator definer = new DefineOperator();
        double[] numbers = new double[names.length];
        String[] args = new String[2];
        for(int index = 0; index < names.length; index++) {
            numbers[index] = rand.nextDouble();
            args[0] = names[index];
            args[1] = Double.toString(numbers[index]);
            definer.run(context, args);
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
        Operator definer = new DefineOperator();
        String[] args = new String[2];
        for(String name: names) {
            args[0] = name;
            args[1] = Double.toString(rand.nextDouble());
            definer.run(context, args);
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
        Operator definer = new DefineOperator();
        double[] numbers = new double[names.length];
        String[] args = new String[2];
        for(int index = 0; index < names.length; index++) {
            numbers[index] = rand.nextDouble();
            args[0] = names[index];
            args[1] = Double.toString(numbers[index]);
            definer.run(context, args);
        }
        for(int index = 0; index < names.length; index++) {
            numbers[index] = rand.nextDouble();
            args[0] = names[index];
            args[1] = Double.toString(numbers[index]);
            definer.run(context, args);
        }
        for(int index = 0; index < names.length; index++) {
            assertEquals(numbers[index], context.getDouble(names[index]));
        }
    }

    @Test
    @DisplayName("define with wrong number of args")
    void defineWithWrongArgc() {
        Operator operator = new DefineOperator();
        Context context = new Context();
        Random rand = new Random();
        context.push(rand.nextDouble());
        for(int index = 0; index < 2; index++) {
            String[] arg0 = new String[index];
            assertThrows(IllegalArgumentException.class, () -> operator.run(context, arg0));
        }
        String[] arg1 = new String[2]; arg1[0] = "a12"; arg1[1] = "12";
        assertDoesNotThrow(() -> operator.run(context, arg1));
        for(int index = 3; index <= 60; index++) {
            String[] args = new String[index];
            assertThrows(IllegalArgumentException.class, () -> operator.run(context, args));
        }
    }

    @Test
    @DisplayName("define with wrong label")
    void defineWithWrongLabel() {
        Operator operator = new DefineOperator();
        Context context = new Context();
        Random rand = new Random();
        String[] args = new String[2];
        args[1] = Double.toString(rand.nextDouble());
        String[] goodLabels = {"_12oeu", ",bbb", "a1", "_44444444", "label"};
        for(String label: goodLabels) {
            args[0] = label;
            assertDoesNotThrow(() -> operator.run(context, args));
        }
        String[] badLabels = {"3a", "9eou", "0label"};
        for(String label: badLabels) {
            args[0] = label;
            assertThrows(IllegalArgumentException.class, () -> operator.run(context, args));
        }
    }
}
