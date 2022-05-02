package mur.lab2.operator;

import mur.lab2.calculator.Context;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class IllegalArgumentsTest {
    @ParameterizedTest
    @ValueSource(doubles = {0.0, 2.4, Double.NaN, Double.NEGATIVE_INFINITY,
            Double.POSITIVE_INFINITY, 13, 1241154, -4.5415414141})
    @DisplayName("divide by zero")
    void divideByZero(double dividend) {
        Operator operator = new DivideOperator();
        Context context = new Context();
        context.push(dividend);
        context.push(0);
        assertThrows(OperatorDivideByZeroException.class, () -> operator.run(context, null));
    }

    @ParameterizedTest
    @ValueSource(doubles = {-.00000000001, -1, -10000, Double.NEGATIVE_INFINITY, -Double.MIN_VALUE})
    @DisplayName("sqrt negative numbers")
    void sqrtNegativeNumbers(double radicand) {
        Operator operator = new SqrtOperator();
        Context context = new Context();
        context.push(radicand);
        assertThrows(OperatorNegativeSqrtException.class, () -> operator.run(context, null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"3a", "9eou", "0label"})
    @DisplayName("define with wrong label")
    void defineWithWrongLabel(String badLabel) {
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
        args[0] = badLabel;
        assertThrows(OperatorIllegalLabelException.class, () -> operator.run(context, args));
    }
}
