package mur.lab3.calculator;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.util.Random;

public class CommandParserTest {
    private static final int ITERATION_COUNT = 15;
    private static final int MIN_ARG_COUNT = 0;
    private static final int MAX_ARG_COUNT = 6;
    private static final int MIN_CMD_COUNT = 1;
    private static final int MAX_CMD_COUNT = 12;

    String randomCommand(String[] commandBank) {
        StringBuilder builder = new StringBuilder();
        Random rand = new Random();

        int argc = rand.nextInt(MAX_ARG_COUNT - MIN_ARG_COUNT + 1) + MIN_ARG_COUNT;
        builder.append(commandBank[rand.nextInt(commandBank.length)]);
        if(argc != 0) {
            builder.append('\t');
            for (int argIndex = 0; argIndex < argc - 1; argIndex++)
                builder.append(rand.nextInt(1000)).append(' ');
            builder.append(rand.nextInt(1000));
        }
        return builder.toString();
    }

    @Test
    @DisplayName("Read and parse commands")
    void parseCommand() {
        try {
            final String fileName = "cmd1.txt";
            CommandParser parser = new CommandParser(fileName);
            Command[] commands = parser.readAllCommands();
            for(Command command: commands)
                System.out.println(command);
            System.out.println();
            parser.close();

            parser = new CommandParser(fileName);
            commands = parser.readAllCommands();
            for(Command command: commands)
                System.out.println(command);
            parser.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Read and parse random commands")
    void parseRandomCommands() {
        final String fileName = "tests/random.txt";
        String[] commandBank = {"PUSH", "POP", "/", "+", "-", "*", "SQRT", "PRINT"};
        Random rand = new Random();
        for(int iteration = 0; iteration < ITERATION_COUNT; iteration++) {
            String[] commands = new String[rand.nextInt(MAX_CMD_COUNT -
                    MIN_CMD_COUNT + 1) + MIN_ARG_COUNT];
            for(int index = 0; index < commands.length; index++)
                commands[index] = randomCommand(commandBank);

            try {
                Writer writer = new OutputStreamWriter(new FileOutputStream(fileName));
                for(String command: commands) {
                    writer.write(command);
                    writer.write('\n');
                }
                writer.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }

            try {
                CommandParser parser = new CommandParser(fileName);
                Command[] parsedCommands = parser.readAllCommands();
                assertEquals(commands.length, parsedCommands.length);
                for(int index = 0; index < commands.length; index++)
                    assertEquals(commands[index], parsedCommands[index].toString());
                parser.close();
            }
            catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
}
