package mur.lab2.calculator;

public class Query {
    private final String commandName;
    private final String[] args;

    public Query(String command, String[] args) {
        this.commandName = command;
        this.args = args;
    }

    public String[] getArgs() {
        return args;
    }

    public String getCommandName() {
        return commandName;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(commandName);
        builder.append('\t');
        for(String arg: args)
            builder.append(arg).append(' ');
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }
}
