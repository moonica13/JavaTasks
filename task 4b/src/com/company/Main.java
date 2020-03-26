package com.company;

import model.Data;
import dao.DataDAO;
import dao.DataDAOImpl;

import java.io.File;
import java.sql.*;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {

        Connection conn = null;

        if (args.length == 0) {
            System.out.println("No arguments!");
            System.exit(-1);
        }

        final int length = args.length;

        try {

            //connection
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/youngdev2020", "moonica13", "zW.99/");

            clearTable(conn);

            CountDownLatch ctdLatch = new CountDownLatch(length);

            DataDAO dataDAO = new DataDAOImpl(conn);

            for (String arg : args) {

                File file = new File(arg);

                Thread thread = new Thread(new ModifThread(file, dataDAO, arg));
                thread.start();

            }

            if (!ctdLatch.await(30, TimeUnit.MILLISECONDS)) {
                System.out.println("Finished " + ctdLatch.getCount() + " / " + length + ".");
                System.out.print(" Whoopsie! time's out. Restart the app.");
            }

            System.out.println("Finished all tasks!");

            System.out.println();
            //Create
            Data tableData = new Data();
            tableData.setSum(133);
            tableData.setFile("input5.txt");

            dataDAO.create(tableData);

            // Find all
            List<Data> allDatas = dataDAO.findAll();

            System.out.println("\n\n Table results : \n");

            for (Data d : allDatas) {
                System.out.println(d);
            }

            //Find by id
            Data perById = dataDAO.findById(3);
            System.out.println("Here's some data: " + perById);

            //Delete
            dataDAO.delete(perById);
            System.out.println("\nDeleting " + tableData.getFile() + "...");

            System.out.println();

            //Find all
            System.out.println("\n\n Table results : \n");

            for (Data d : allDatas) {
                System.out.println(d);
            }


        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static void clearTable(Connection conn) {
        try {
            conn.createStatement().execute("truncate table task4");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}