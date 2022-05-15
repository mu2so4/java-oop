package mur.lab2.operator;

import java.io.IOException;
import java.util.*;
import java.util.logging.*;

public class OperatorFactory {
    private static final Logger factoryLogger = Logger.getLogger(OperatorFactory.class.getCanonicalName());

    public static Map<String, Operator> loadCommandsFromResource(String fileName)
            throws IOException {
        Map<String, Operator> operators = new HashMap<>();
        try(CommandConfigParser parser = new CommandConfigParser(fileName)) {
            Command command;
            while(true) {
                try {
                    command = parser.nextCommand();
                    if(command == null)
                        break;
                    operators.put(command.getCommandName(), newInstance(command.getFullClassName()));
                    factoryLogger.info(String.format("%s\tloaded as %s",
                            command.getFullClassName(), command.getCommandName()));
                }
                catch(Exception e) {
                    factoryLogger.log(Level.SEVERE, String.format("%s: %s", e.getClass().getCanonicalName(), e.getMessage()));
                }
            }
        }
        return operators;
    }

    public static Operator newInstance(String fullClassName)
            throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Object obj;
        obj = Class.forName(fullClassName).newInstance();
        if(!(obj instanceof Operator))
            throw new ClassCastException(fullClassName +
                    " is not a subclass for " + Operator.class.getCanonicalName());
        return (Operator) obj;
    }
}
