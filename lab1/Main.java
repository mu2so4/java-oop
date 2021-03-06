package lab1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String inputFileName, outputFileName;
        if(args.length != 2) {
            Scanner scanner = new Scanner(System.in);
            inputFileName = scanner.next();
            outputFileName = scanner.next();
            scanner.close();
        }
        else {
            inputFileName = args[0];
            outputFileName = args[1];
        }
        DictionaryReader reader = new DictionaryReader();
        Dictionary dictionary = reader.read(inputFileName);
        DictionaryWriter writer = new DictionaryWriter();
        writer.write(dictionary, outputFileName);
    }
}
