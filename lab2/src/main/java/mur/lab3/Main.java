package mur.lab3;

import mur.lab3.calculator.*;
import mur.lab3.operator.*;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.addOperator("DEFINE", DefineOperator.class.getCanonicalName());
        calculator.addOperator("/", DivideOperator.class.getCanonicalName());
        calculator.addOperator("-", MinusOperator.class.getCanonicalName());
        calculator.addOperator("*", MultiplyOperator.class.getCanonicalName());
        calculator.addOperator("+", PlusOperator.class.getCanonicalName());
        calculator.addOperator("POP", PopOperator.class.getCanonicalName());
        calculator.addOperator("PRINT", PrintOperator.class.getCanonicalName());
        calculator.addOperator("PUSH", PushOperator.class.getCanonicalName());
        calculator.addOperator("#", SkipOperator.class.getCanonicalName());
        calculator.addOperator("SQRT", SqrtOperator.class.getCanonicalName());

        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.next();
        scanner.close();
        try {
            CommandParser parser = new CommandParser(fileName);
            Command command = parser.nextCommand();
            while(command != null) {
                calculator.execute(command);
                command = parser.nextCommand();
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
}
