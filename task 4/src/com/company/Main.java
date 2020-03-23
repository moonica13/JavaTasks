package com.company;

import java.io.File;
import java.sql.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("No arguments!");
            System.exit(-1);
        }

        final int length = args.length;

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/youngdev2020", "moonica13", "zW.99/")) {

            try {
                conn.createStatement().execute("truncate table task4");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            CountDownLatch ctdLatch = new CountDownLatch(length);

            for (String arg : args) {

                File file = new File(arg);

                Thread thread = new Thread(new ModifThread(file, conn, arg));
                thread.start();

            }

            if (!ctdLatch.await(30, TimeUnit.MILLISECONDS)) {
                System.out.println("Finished " + ctdLatch.getCount() + " / " + length + ".");
                System.out.print(" Whoopsie! time's out. Restart the app.");
            }

            System.out.println("Finished all tasks!");

            try (Statement statement = conn.createStatement()) {

                try (ResultSet resultSet = statement.executeQuery("select * from task4")) {
                    System.out.println("\n\n Table results : \n");
                    System.out.println(" fisier | suma ");
                    System.out.println("--------+------");

                    while (resultSet.next()) {
                        System.out.println(resultSet.getString("fisier") + " | " + resultSet.getString("suma"));
                    }

                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
