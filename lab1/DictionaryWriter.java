package lab1;

import java.io.*;

public class DictionaryWriter {

    private String wordDoCSV(Word word, int dictionarySize) {
        return word.getLexeme() + "\t" + word.getFrequency() + "\t" +
                String.format("%.2f%%\n", 100. * word.getFrequency() / dictionarySize);
    }

    public void write(Dictionary dictionary, String outputFileName) {
        Writer writer = null;
        try {
            writer = new OutputStreamWriter(new FileOutputStream(outputFileName));
            Word[] words = dictionary.getDescendingDictionary();
            int size = dictionary.getDictionarySize();
            for(Word word: words)
                writer.write(wordDoCSV(word, size));
        }
        catch (IOException e) {
            System.err.println("Error while writing file: " + e.getLocalizedMessage());
        }
        finally {
            if(null != writer) {
                try {
                    writer.close();
                }
                catch(IOException e) {
                    e.printStackTrace(System.err);
                }
            }
        }
    }
}
