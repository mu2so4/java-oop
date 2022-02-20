package lab1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String inputFileName, outputFileName;
        if(args.length != 3) {
            Scanner scanner = new Scanner(System.in);
            inputFileName = scanner.next();
            outputFileName = scanner.next();
            scanner.close();
        }
        else {
            inputFileName = args[1];
            outputFileName = args[2];
        }
        Dictionary dictionary = new Dictionary();
        DictionaryReader reader = new DictionaryReader(inputFileName);
        reader.read(dictionary);
        DictionaryWriter writer = new DictionaryWriter(outputFileName);
        writer.write(dictionary);
    }
}
