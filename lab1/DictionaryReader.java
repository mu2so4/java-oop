package lab1;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class DictionaryReader {
    private final String fileName;

    public DictionaryReader(String fileName) {
        this.fileName = fileName;
    }

    public void read(Dictionary dictionary) {
        Reader reader = null;
        try {
            reader = new InputStreamReader(new FileInputStream(fileName));
            StringBuilder str = new StringBuilder();
            for(int ch = reader.read(); ch != -1; ch = reader.read()) {
                if(Character.isLetterOrDigit(ch)) {
                    ch = Character.toLowerCase(ch);
                    str.append((char) ch);
                }

                else if(str.length() != 0) {
                    dictionary.insert(str.toString());
                    str.setLength(0);
                }
            }
            if(!str.toString().equals(""))
                dictionary.insert(str.toString());
        }
        catch (IOException e) {
            System.err.println("Error while reading file: " + e.getLocalizedMessage());
        }
        finally {
            if (null != reader) {
                try {
                    reader.close();
                }
                catch (IOException e) {
                    e.printStackTrace(System.err);
                }
            }
        }
    }
}
