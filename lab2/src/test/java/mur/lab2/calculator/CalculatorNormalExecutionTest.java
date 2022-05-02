package mur.lab2.calculator;

import mur.lab2.operator.*;
import org.junit.jupiter.api.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorNormalExecutionTest {
    @Test
    @DisplayName("executing testing")
    void execute() throws OperatorException, IOException {
        Calculator calculator = new Calculator("operators.conf");

        Context outerContext = new Context();

        try(QueryParser parser = new QueryParser("tests/cmd1.txt")) {
            Query cmd = parser.nextQuery();
            while(cmd != null) {
                assertEquals(calculator.execute(cmd),
                        calculator.getOperator(cmd.getCommandName()).run(outerContext, cmd.getArgs()));
                cmd = parser.nextQuery();
            }
        }
    }
}
