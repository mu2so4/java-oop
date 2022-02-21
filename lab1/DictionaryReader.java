package lab1;

import java.io.*;

public class DictionaryReader {
    private boolean hasNext = true;

    private String parse(Reader reader) throws IOException {
        StringBuilder str = new StringBuilder();
        int ch = reader.read();
        hasNext = -1 != ch;
        while(!Character.isLetterOrDigit(ch) && -1 != ch)
            ch = reader.read();
        for(; ch != -1; ch = reader.read()) {
            if(Character.isLetterOrDigit(ch)) {
                ch = Character.toLowerCase(ch);
                str.append((char) ch);
            }
            else break;
        }
        return str.toString();
    }

    public Dictionary read(String inputFileName) {
        Dictionary dictionary = new Dictionary();
        Reader reader = null;
        try {
            reader = new InputStreamReader(new FileInputStream(inputFileName));
            while(hasNext) {
                dictionary.insert(parse(reader));
            }
        }
        catch (IOException e) {
            System.err.println("Error while reading file: " + e.getLocalizedMessage());
        }
        finally {
            if(null != reader) {
                try {
                    reader.close();
                }
                catch(IOException e) {
                    e.printStackTrace(System.err);
                }
            }
        }

        return dictionary;
    }
}
