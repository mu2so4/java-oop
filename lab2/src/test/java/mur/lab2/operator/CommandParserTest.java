package mur.lab2.operator;


import org.junit.jupiter.api.*;

import java.io.IOException;

public class CommandParserTest {
    @Test
    @DisplayName("parsing operator config")
    void parse() throws IOException, CommandConfigFileException {
        try(CommandConfigParser parser = new CommandConfigParser("operators.conf")) {
            Command command = parser.nextCommand();
            System.out.println(command);
        }
    }
}
