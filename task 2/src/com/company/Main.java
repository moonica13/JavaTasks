package com.company;

import java.io.*;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.TreeSet;
import java.util.Set;

import static java.lang.Integer.parseInt;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        String fileName = "date.txt";
        String filePath = java.nio.file.Paths.get(".").toAbsolutePath() + File.separator + fileName;
        File NewFile = createNewFileFromPath(filePath);

        System.out.print(" Hey, buddy, let's do something really cool. ");

        try (Scanner scanner = new Scanner(System.in))
        {
            Set<Integer> numbers = new TreeSet<>();
            while (true)
            {
                System.out.print("Read the number: ");
                String string = scanner.next();
                try
                {
                    int number = parseInt(string);

                    if (!numbers.add(number)) break;

                } catch (NumberFormatException e) {
                    System.out.println("Oopsies! That's not a number, buddy!");
                }
            }

            try (PrintWriter PrintToFile = new PrintWriter(NewFile))
            {
                PrintToFile.println(numbers.stream().map(Object::toString).collect(Collectors.joining(" , ")) + " .");
            }

        }

        try (BufferedReader BuffReader = new BufferedReader(new FileReader(NewFile)))
        {
            System.out.println(BuffReader.readLine());
        }
    }


    private static File createNewFileFromPath(String filePath) throws IOException
    {
        File file;
        file = new File(filePath);

        if (file.exists()) file.delete();
            file.createNewFile();
        return file;
    }

}