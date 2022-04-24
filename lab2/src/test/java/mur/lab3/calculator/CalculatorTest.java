package mur.lab3.calculator;

import mur.lab3.operator.*;
import org.junit.jupiter.api.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {
    @Test
    @DisplayName("operator loading...")
    void loadOperators() {
        Calculator calculator = new Calculator();
        assertDoesNotThrow(() -> calculator.addOperator("DEFINE", DefineOperator.class.getCanonicalName()));
        assertDoesNotThrow(() -> calculator.addOperator("/", DivideOperator.class.getCanonicalName()));
        assertDoesNotThrow(() -> calculator.addOperator("-", MinusOperator.class.getCanonicalName()));
        assertDoesNotThrow(() -> calculator.addOperator("*", MultiplyOperator.class.getCanonicalName()));
        assertDoesNotThrow(() -> calculator.addOperator("+", PlusOperator.class.getCanonicalName()));
        assertDoesNotThrow(() -> calculator.addOperator("POP", PopOperator.class.getCanonicalName()));
        assertDoesNotThrow(() -> calculator.addOperator("PRINT", PrintOperator.class.getCanonicalName()));
        assertDoesNotThrow(() -> calculator.addOperator("PUSH", PushOperator.class.getCanonicalName()));
        assertDoesNotThrow(() -> calculator.addOperator("#", SkipOperator.class.getCanonicalName()));
        assertDoesNotThrow(() -> calculator.addOperator("SQRT", SqrtOperator.class.getCanonicalName()));

        try {
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
        } catch (NoSuchOperatorException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("executing testing")
    void execute() {
        Calculator calculator = new Calculator();
        calculator.addOperator("DEFINE", DefineOperator.class.getCanonicalName());
        calculator.addOperator("/", DivideOperator.class.getCanonicalName());
        calculator.addOperator("-", MinusOperator.class.getCanonicalName());
        calculator.addOperator("*", MultiplyOperator.class.getCanonicalName());
        calculator.addOperator("+", PlusOperator.class.getCanonicalName());
        calculator.addOperator("POP", PopOperator.class.getCanonicalName());
        calculator.addOperator("PRINT", PrintOperator.class.getCanonicalName());
        calculator.addOperator("PUSH", PushOperator.class.getCanonicalName());
        calculator.addOperator("#", SkipOperator.class.getCanonicalName());
        calculator.addOperator("SQRT", SqrtOperator.class.getCanonicalName());

        Context outerContext = new Context();

        CommandParser parser = null;
        try {
            parser = new CommandParser("tests/cmd1.txt");
            Command cmd = parser.nextCommand();
            while(cmd != null) {
                assertEquals(calculator.execute(cmd),
                        calculator.getOperator(cmd.getCommandName()).run(outerContext, cmd.getArgs()));
                cmd = parser.nextCommand();
            }
        } catch (IOException | NoSuchOperatorException e) {
            e.printStackTrace();
        } finally {
            if(parser != null) {
                try {
                    parser.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
