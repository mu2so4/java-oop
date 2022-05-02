package mur.lab2.operator;

import mur.lab2.calculator.Context;
import mur.lab2.calculator.ContextEmptyStackException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class EmptyStackTest {
    @ParameterizedTest
    @ValueSource(classes = {PlusOperator.class, MinusOperator.class, MultiplyOperator.class,
            DivideOperator.class, SqrtOperator.class, PopOperator.class, PrintOperator.class})
    @DisplayName("Operate with no numbers on a stack")
    void testEmptyStack(Class<Operator> operatorClass)
            throws InstantiationException, IllegalAccessException,
            NoSuchMethodException, InvocationTargetException {
        Operator operator = operatorClass.getDeclaredConstructor().newInstance();
        Context context = new Context();
        assertThrows(OperatorTooFewElementsInStackException.class, () -> operator.run(context, null));
    }

    @ParameterizedTest
    @ValueSource(classes = {PlusOperator.class, MinusOperator.class, MultiplyOperator.class,
            DivideOperator.class})
    @DisplayName("Operate with one number on a stack")
    void testWithOneElementOnStack(Class<Operator> operatorClass)
            throws NoSuchMethodException, InvocationTargetException,
            InstantiationException, IllegalAccessException, ContextEmptyStackException {
        Operator operator = operatorClass.getDeclaredConstructor().newInstance();
        Context context = new Context();
        Random rand = new Random();
        double number = rand.nextDouble();
        context.push(number);
        assertThrows(OperatorTooFewElementsInStackException.class, () -> operator.run(context, null));
        assertEquals(number, context.pop());
    }
}
