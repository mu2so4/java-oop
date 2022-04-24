package mur.lab3.calculator;

import java.io.*;
import java.util.ArrayList;

public class CommandParser implements Closeable {
    private final Reader reader;

    public CommandParser(String filename) throws FileNotFoundException {
        reader = new InputStreamReader(new FileInputStream(filename));
    }

    private StringBuilder nextWord() throws IOException {
        StringBuilder builder = new StringBuilder();
        int currentChar = reader.read();
        if(currentChar < 0)
            return null;
        while(!Character.isWhitespace(currentChar)) {
            builder.append((char) currentChar);
            currentChar = reader.read();
        }
        builder.append((char) currentChar);
        return builder;
    }

    public Command nextCommand() throws IOException {
        StringBuilder builder = nextWord();
        if(builder == null)
            return null;
        char whitespace = builder.charAt(builder.length() - 1);
        builder.deleteCharAt(builder.length() - 1);
        String commandName = builder.toString();
        ArrayList<String> args = new ArrayList<>();
        while(whitespace == ' ' || whitespace == '\t') {
            builder = nextWord();
            if(builder == null)
                break;
            whitespace = builder.charAt(builder.length() - 1);
            builder.deleteCharAt(builder.length() - 1);
            args.add(builder.toString());
        }
        String[] argsArray = new String[args.size()];
        args.toArray(argsArray);
        return new Command(commandName, argsArray);
    }

    Command[] readAllCommands() throws IOException {
        ArrayList<Command> commands = new ArrayList<>();
        for(Command command = nextCommand(); command != null; command = nextCommand())
            commands.add(command);
        Command[] commandArray = new Command[commands.size()];
        commands.toArray(commandArray);
        return commandArray;
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }
}
