package mur.lab2;

import mur.lab2.calculator.*;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Calculator calculator;
        try {
            calculator = new Calculator("operators.conf");
        }
        catch(IOException e) {
            throw new RuntimeException(e);
        }

        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.next();
        scanner.close();
        QueryParser parser = null;
        try {
            parser = new QueryParser(fileName);
            Query query = parser.nextQuery();
            while(query != null) {
                calculator.execute(query);
                query = parser.nextQuery();
            }
        }
        catch(IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            if(parser != null) {
                try {
                    parser.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
