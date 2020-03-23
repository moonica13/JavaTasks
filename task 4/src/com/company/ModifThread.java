package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ModifThread extends Thread implements Runnable {

    protected File file;
    protected Connection conn;
    protected String arg;

    public ModifThread(File file, Connection conn, String arg) {
        this.file = file;
        this.conn = conn;
        this.arg = arg;
    }


    @Override
    public void run() {

        int sum = 0;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String st = null;

            while ((st = br.readLine()) != null) {
                int parsedValue = Integer.parseInt(st);
                sum += parsedValue;
            }

            try (Statement statement = conn.createStatement()) {
                statement.executeQuery("INSERT INTO task4 (suma, fisier) VALUES(sum, arg)");
            }


        } catch (IOException | SQLException e) {
            e.printStackTrace();
        } finally {
                try {
                    if (br != null) {
                        br.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

        }
    }
}
