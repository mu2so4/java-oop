package mur.lab2.operator;

public class Command {
    private final String commandName;
    private final String fullClassName;

    public Command(String commandName, String fullClassName) {
        this.commandName = commandName;
        this.fullClassName = fullClassName;
    }

    public String getCommandName() {
        return commandName;
    }

    public String getFullClassName() {
        return fullClassName;
    }

    @Override
    public String toString() {
        return String.format("%s\t%s", commandName, fullClassName);
    }
}
