package mur.lab2.operator;

import mur.lab2.calculator.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OperatorNormalWorkTest {
    private static final double MAX_RANDOM_NUMBER = 1000;
    private static final int NUMBERS_COUNT = 120;

    @Test
    @DisplayName("define with the same names")
    void defineOperator() throws OperatorException, ContextLabelNotFoundException {
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
    @DisplayName("divide one number to other")
    void divide() throws OperatorException {
        Operator operator = new DivideOperator();
        Context context = new Context();
        final double[] numbers = new double[NUMBERS_COUNT];
        Random a = new Random();
        for(int index = 0; index < NUMBERS_COUNT; index++) {
            numbers[index] = a.nextDouble() * 2 * MAX_RANDOM_NUMBER - MAX_RANDOM_NUMBER;
            if(numbers[index] == 0)
                numbers[index] = 1.1;
            context.push(numbers[index]);
        }
        double trueDiv = numbers[NUMBERS_COUNT - 1];
        for(int index = NUMBERS_COUNT - 2; index >= 0; index--) {
            trueDiv = numbers[index] / trueDiv;
            assertEquals(trueDiv, operator.run(context, null));
        }
    }

    @Test
    @DisplayName("subtract one number from other")
    void subtract() throws OperatorException {
        Operator operator = new MinusOperator();
        Context context = new Context();
        final double[] numbers = new double[NUMBERS_COUNT];
        Random rand = new Random();
        for(int index = 0; index < NUMBERS_COUNT; index++) {
            numbers[index] = rand.nextDouble() * 2 * MAX_RANDOM_NUMBER - MAX_RANDOM_NUMBER;
            context.push(numbers[index]);
        }
        double trueSub = numbers[NUMBERS_COUNT - 1];
        for(int index = NUMBERS_COUNT - 2; index >= 0; index--) {
            trueSub = numbers[index] - trueSub;
            assertEquals(trueSub, operator.run(context, null));
        }
    }

    @Test
    @DisplayName("multiply two numbers")
    void multiply() throws OperatorException {
        Operator operator = new MultiplyOperator();
        Context context = new Context();
        final double[] numbers = new double[NUMBERS_COUNT];
        Random rand = new Random();
        for(int index = 0; index < NUMBERS_COUNT; index++) {
            numbers[index] = rand.nextDouble() * 2 * MAX_RANDOM_NUMBER - MAX_RANDOM_NUMBER;
            context.push(numbers[index]);
        }
        double trueMul = numbers[NUMBERS_COUNT - 1];
        for(int index = NUMBERS_COUNT - 2; index >= 0; index--) {
            trueMul *= numbers[index];
            assertEquals(trueMul, operator.run(context, null));
        }
    }

    @Test
    @DisplayName("Add two numbers")
    void add() throws OperatorException {
        Operator operator = new PlusOperator();
        Context context = new Context();
        final double[] numbers = new double[NUMBERS_COUNT];
        Random a = new Random();
        for(int index = 0; index < NUMBERS_COUNT; index++) {
            numbers[index] = a.nextDouble() * 2 * MAX_RANDOM_NUMBER - MAX_RANDOM_NUMBER;
            context.push(numbers[index]);
        }
        double trueSum = numbers[NUMBERS_COUNT - 1];
        for(int index = NUMBERS_COUNT - 2; index >= 0; index--) {
            trueSum += numbers[index];
            assertEquals(trueSum, operator.run(context, null));
        }
    }

    @Test
    @DisplayName("Pop numbers")
    void pop() throws OperatorException {
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
    @DisplayName("print elements")
    void print() throws OperatorException, ContextEmptyStackException {
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
    @DisplayName("push numbers")
    void push() throws OperatorException, ContextEmptyStackException {
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
    @DisplayName("comment")
    void comment() throws OperatorException, ContextEmptyStackException {
        Operator operator = new SkipOperator();
        Context context = new Context();
        Random rand = new Random();
        double[] data = new double[NUMBERS_COUNT];
        for(int index = 0; index < NUMBERS_COUNT; index++) {
            data[index] = rand.nextDouble() * MAX_RANDOM_NUMBER * 2 - MAX_RANDOM_NUMBER;
            context.push(data[index]);
        }
        operator.run(context, null);
        for(int index = NUMBERS_COUNT - 1; index >= 0; index--) {
            assertEquals(data[index], context.pop());
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0, 4, 2, 16, 1.0001111111111, Double.MIN_VALUE, Double.POSITIVE_INFINITY})
    @DisplayName("sqrt numbers")
    void sqrt(double radicand) throws OperatorException, ContextEmptyStackException {
        Operator operator = new SqrtOperator();
        Context context = new Context();
        context.push(radicand);
        double expected = Math.sqrt(radicand);
        assertEquals(expected, operator.run(context, null));
        assertEquals(expected, context.pop());
    }
}
