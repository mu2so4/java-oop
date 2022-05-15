package mur.lab2.calculator;

import mur.lab2.operator.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.logging.*;

public class Calculator {
    private final Context context = new Context();
    private final Map<String, Operator> operators;
    private static final Logger calculatorLogger = Logger.getLogger(Calculator.class.getCanonicalName());

    public Calculator(String configResource)
            throws IOException {
        operators = OperatorFactory.loadCommandsFromResource(configResource);
    }

    Operator getOperator(String commandName) throws NoSuchOperatorException {
        Operator operator = operators.get(commandName);
        if(operator == null)
            throw new NoSuchOperatorException(String.format("operator %s not found", commandName));
        return operator;
    }

    public double execute(Query query) {
        String commandName = query.getCommandName();
        Operator operator;
        double result = Double.NaN;

        try {
            operator = getOperator(commandName);
            result = operator.run(context, query.getArgs());
            calculatorLogger.info(String.format("%s:\tperformed with result %f", commandName, result));
        }
        catch(OperatorException e) {
            calculatorLogger.log(Level.SEVERE, String.format("%s:\t%s", commandName, e.getMessage()));
        }
        return result;
    }

}
