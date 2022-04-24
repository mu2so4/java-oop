package mur.lab3.calculator;

import org.junit.jupiter.api.*;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class CommandTest {
    private static final int MAX_STRING_LENGTH = 30;

    String randomString(int length) {
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        for(int index = 0; index < length; index++)
            builder.append((char) random.nextInt(256));
        return builder.toString();
    }

    @Test
    @DisplayName("Command class test")
    void test() {
        Random random = new Random();
        for(int iteration = 0; iteration < 20; iteration++) {
            String commandName = randomString(1 + random.nextInt(MAX_STRING_LENGTH));
            String[] args = new String[random.nextInt(15)];
            for (int index = 0; index < args.length; index++)
                args[index] = randomString(1 + random.nextInt(MAX_STRING_LENGTH));
            Command command = new Command(commandName, args);
            assertSame(commandName, command.getCommandName());
            assertSame(args, command.getArgs());
        }
    }

    @Test
    @DisplayName("toString test")
    void testToString() {
        Random random = new Random();
        for(int iteration = 0; iteration < 20; iteration++) {
            String commandName = randomString(1 + random.nextInt(MAX_STRING_LENGTH));
            String[] args = new String[random.nextInt(15)];
            for (int index = 0; index < args.length; index++)
                args[index] = randomString(1 + random.nextInt(MAX_STRING_LENGTH));
            Command command = new Command(commandName, args);
            StringBuilder builder = new StringBuilder(commandName);
            builder.append('\t');
            for(String arg: args)
                builder.append(arg).append(' ');
            builder.deleteCharAt(builder.length() - 1);
            assertEquals(builder.toString(), command.toString());
        }
    }
}
