package com.company;

import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) {
        Scanner S = new Scanner(System.in);

        int ok = 1, p = 1;
        String nms[] = new String[5];

        System.out.print("Nume: ");
        nms[0] = S.nextLine();

        for (int i = 1; i < 5; i++) {
            ok = 1;

            System.out.print("Nume: ");

            String name = S.nextLine();

            for (int j = 0; j < i; j++) {
                if (name.equalsIgnoreCase(v[j])) {
                    ok = 0;
                }
            }

            if (ok == 1) {
                nms[p++] = name;
            }
        }

        String fileSavingPath = "D:/Intellij/src/com/company/";
        Map<Character, List<String>> fileWriters = new HashMap<>(5);

        fileWriters.put('A', new ArrayList<>());
        fileWriters.put('M', new ArrayList<>());
        fileWriters.put('N', new ArrayList<>());
        fileWriters.put('T', new ArrayList<>());
        fileWriters.put('L', new ArrayList<>());

        new File(fileSavingPath).mkdirs();

        for (int i = 0; i < p; i++) {
            char firstChar = nms[i].charAt(0);
            List<String> nume = fileWriters.get(firstChar);

            if (nume != null)
                nume.add(nms[i]);
        }

        for (Character initiala : fileWriters.keySet()) {
            String fileName = "NumeCu" + initiala;

            try (FileWriter fileWriter = new FileWriter(fileSavingPath + fileName)) {
                List<String> names = fileWriters.get(initiala);

                if (names != null) {
                    for (String name : names)
                        fileWriter.write(name + '\n');
                }
            } catch (IOException e) {
                System.out.println(" ERROR! ");
                e.printStackTrace();
            }
        }
    }
}
