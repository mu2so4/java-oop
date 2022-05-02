package mur.lab2.operator;

import java.io.*;

public class CommandConfigParser implements AutoCloseable {
    private final InputStream resource;

    public CommandConfigParser(String resourceName) {
        resource = OperatorFactory.class.getClassLoader().getResourceAsStream(resourceName);
    }

    private StringBuilder nextLine() throws IOException {
        StringBuilder builder = new StringBuilder();
        int currentChar = resource.read();
        if(currentChar < 0)
            return null;
        while(currentChar != '\n' && currentChar > 0) {
            builder.append((char) currentChar);
            currentChar = resource.read();
        }
        builder.append((char) currentChar);
        return builder;
    }

    public Command nextCommand()
            throws IOException, CommandConfigFileException {
        StringBuilder line = nextLine();
        if(line == null)
            return null;
        String[] split = line.toString().split("\\s");
        if(split.length < 2) {
            throw new CommandConfigFileException(
                    String.format("too few arguments for a command: %s", split.length));
        }
        if(split.length > 2) {
            throw new CommandConfigFileException(
                    String.format("too many arguments for a command: %s", split.length));
        }
        return new Command(split[0], split[1]);
    }

    @Override
    public void close() throws IOException {
        resource.close();
    }
}
