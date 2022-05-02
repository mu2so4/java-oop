package mur.lab2.calculator;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class QueryParser implements AutoCloseable {
    private final Reader reader;

    public QueryParser(String filename) throws FileNotFoundException {
        reader = new InputStreamReader(new FileInputStream(filename));
    }

    private StringBuilder nextLine() throws IOException {
        StringBuilder builder = new StringBuilder();
        int currentChar = reader.read();
        if(currentChar < 0)
            return null;
        while(currentChar != '\n' && currentChar > 0) {
            builder.append((char) currentChar);
            currentChar = reader.read();
        }
        builder.append((char) currentChar);
        return builder;
    }

    public Query nextQuery() throws IOException {
        StringBuilder line = nextLine();
        if(line == null)
            return null;
        String[] words = line.toString().split("\\s");
        String[] args = Arrays.copyOfRange(words, 1, words.length);
        return new Query(words[0], args);
    }

    Query[] readAllCommands() throws IOException {
        ArrayList<Query> commands = new ArrayList<>();
        for(Query query = nextQuery(); query != null; query = nextQuery())
            commands.add(query);
        Query[] queryArray = new Query[commands.size()];
        commands.toArray(queryArray);
        return queryArray;
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }
}
