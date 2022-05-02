package mur.lab2.operator;

import mur.lab2.calculator.Context;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ArgCountTest {
    @ParameterizedTest
    @ValueSource(classes = {DivideOperator.class, MinusOperator.class, SqrtOperator.class,
    MultiplyOperator.class, PlusOperator.class, PopOperator.class, PrintOperator.class})
    @DisplayName("operate with various numbers of args via zero-argc operators")
    void test0ArgcOperators(Class<Operator> operatorClass) throws NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException {
        Operator operator = operatorClass.getDeclaredConstructor().newInstance();
        Context context = new Context();
        Random rand = new Random();
        context.push(rand.nextDouble());
        context.push(rand.nextDouble());
        context.push(rand.nextDouble());
        String[] arg0 = new String[0];
        assertDoesNotThrow(() -> operator.run(context, arg0));
        String[] args = new String[rand.nextInt(100) + 1];
        assertThrows(OperatorIllegalArgumentCountException.class, () -> operator.run(context, args));
    }

    @ParameterizedTest
    @ValueSource(classes = {PushOperator.class})
    @DisplayName("operate with various numbers of args via 1-argc operators")
    void test1ArgcOperators(Class<Operator> operatorClass) throws NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException {
        Operator operator = operatorClass.getDeclaredConstructor().newInstance();
        Context context = new Context();
        Random rand = new Random();
        context.push(rand.nextDouble());
        String[] arg0 = new String[0];
        assertThrows(OperatorIllegalArgumentCountException.class, () -> operator.run(context, arg0));
        String[] arg1 = new String[1]; arg1[0] = "12";
        assertDoesNotThrow(() -> operator.run(context, arg1));
        for(int index = 2; index <= 60; index++) {
            String[] args = new String[index];
            assertThrows(OperatorIllegalArgumentCountException.class, () -> operator.run(context, args));
        }
    }

    @Test
    @DisplayName("define with various numbers of args")
    void defineArgc() {
        Operator operator = new DefineOperator();
        Context context = new Context();
        Random rand = new Random();
        context.push(rand.nextDouble());
        for(int index = 0; index < 2; index++) {
            String[] arg0 = new String[index];
            assertThrows(OperatorIllegalArgumentCountException.class, () -> operator.run(context, arg0));
        }
        String[] arg1 = new String[2]; arg1[0] = "a12"; arg1[1] = "12";
        assertDoesNotThrow(() -> operator.run(context, arg1));
        for(int index = 3; index <= 60; index++) {
            String[] args = new String[index];
            assertThrows(OperatorIllegalArgumentCountException.class, () -> operator.run(context, args));
        }
    }

    @ParameterizedTest
    @ValueSource(classes = {SkipOperator.class})
    @DisplayName("operate with various numbers of args via 2-argc operators")
    void testAnyArgcOperators(Class<Operator> operatorClass) throws NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException {
        Operator operator = operatorClass.getDeclaredConstructor().newInstance();
        Context context = new Context();
        for(int index = 0; index <= 1000; index++) {
            String[] args = new String[index];
            assertDoesNotThrow(() -> operator.run(context, args));
        }
    }
}
