package org.example.TestTaskAikam;

import org.example.TestTaskAikam.Parse.FileErrorWriter;
import org.example.TestTaskAikam.Parse.FileSearchReader;
import org.example.TestTaskAikam.Parse.FileStatReader;

public class Main {
    public static void main(String[] args) {
        if (args.length == 3) {
            String operation = args[0];
            String inputPath = args[1];
            String outputPath = args[2];

            switch (operation) {
                case "search":
                    new FileSearchReader().searchRead(inputPath, outputPath);
                    break;
                case "stat":
                    new FileStatReader().statRead(inputPath, outputPath);
                    break;
                default:
                    new FileErrorWriter().writeError("Неверно указана операция. Доступны операции search и stat.", outputPath);
            }
        }
        else {
            System.out.println("Неверное количество аргументов. Необходимо только 3.");
        }
    }
}
