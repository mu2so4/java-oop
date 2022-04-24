package mur.lab3.calculator;

import mur.lab3.operator.*;

import java.util.HashMap;
import java.util.logging.*;

public class Calculator {
    private final Context context = new Context();
    private final HashMap<String, Operator> operators = new HashMap<>();
    private final Logger calculatorLogger = Logger.getLogger(Calculator.class.getCanonicalName());

    public void addOperator(String commandName, String fullClassName) {
        try {
            operators.put(commandName, OperatorFactory.newInstance(fullClassName));
            calculatorLogger.fine(fullClassName + " loaded as command " + commandName);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | ClassCastException e) {
            calculatorLogger.log(Level.SEVERE, "loading " + fullClassName + " failed", e);
        }
    }

    Operator getOperator(String commandName) throws NoSuchOperatorException {
        Operator operator = operators.get(commandName);
        if(operator == null)
            throw new NoSuchOperatorException("operator " + commandName + " not found");
        return operator;
    }

    public double execute(Command command) {
        String commandName = command.getCommandName();
        Operator operator;
        double result = Double.NaN;

        try {
            operator = getOperator(commandName);
            result = operator.run(context, command.getArgs());
            calculatorLogger.fine(commandName + "\tperformed with result " + result + "\n");
        }
        catch(NoSuchOperatorException e) {
            calculatorLogger.log(Level.SEVERE, commandName + ": command not found", e);
        }
        catch(Exception e) {
            calculatorLogger.log(Level.SEVERE, "got exception from command "
                    + commandName, e);
        }
        return result;
    }

}
