package mur.lab2.calculator;

import mur.lab2.operator.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorLoadingTest {
    @Test
    @DisplayName("loading operators to calculator")
    void loadOperators() throws IOException, NoSuchOperatorException {
        Calculator calculator = new Calculator("operators.conf");

        assertEquals(calculator.getOperator("DEFINE").getClass(), DefineOperator.class);
        assertEquals(calculator.getOperator("/").getClass(), DivideOperator.class);
        assertEquals(calculator.getOperator("-").getClass(), MinusOperator.class);
        assertEquals(calculator.getOperator("*").getClass(), MultiplyOperator.class);
        assertEquals(calculator.getOperator("+").getClass(), PlusOperator.class);
        assertEquals(calculator.getOperator("POP").getClass(), PopOperator.class);
        assertEquals(calculator.getOperator("PRINT").getClass(), PrintOperator.class);
        assertEquals(calculator.getOperator("PUSH").getClass(), PushOperator.class);
        assertEquals(calculator.getOperator("#").getClass(), SkipOperator.class);
        assertEquals(calculator.getOperator("SQRT").getClass(), SqrtOperator.class);

    }
}
