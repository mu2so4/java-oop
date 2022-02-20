package lab1;

import java.util.Iterator;
import java.util.TreeSet;
import java.io.*;

public class DictionaryWriter {
    private final String fileName;

    public DictionaryWriter(String fileName) {
        this.fileName = fileName;
    }

    void write(Dictionary dictionary) {
        int size = dictionary.getDictionarySize();
        TreeSet<Pair<Integer, String>> stats = dictionary.getDescendingDictionary();

        Writer writer = null;
        try {
            writer = new OutputStreamWriter(new FileOutputStream(fileName));
            Iterator<Pair<Integer, String>> descendingIterator = stats.descendingIterator();
            while(descendingIterator.hasNext()) {
                Pair<Integer, String> pair = descendingIterator.next();
                writer.write(pair.getValue() + "\t" + pair.getKey() + "\t" +
                         String.format("%.2f%%\n", 100. * pair.getKey() / size));
            }
        }
        catch (IOException e) {
            System.err.println("Error while writing file: " + e.getLocalizedMessage());
        }
        finally {
            if (null != writer) {
                try {
                    writer.close();
                }
                catch (IOException e) {
                    e.printStackTrace(System.err);
                }
            }
        }
    }
}
