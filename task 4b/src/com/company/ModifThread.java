package com.company;

import dao.DataDAO;
import model.Data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ModifThread extends Thread implements Runnable {

    protected File file;
    protected DataDAO dataDAO;
    protected String arg;

    public ModifThread(File file, DataDAO dataDAO, String arg) {
        this.file = file;
        this.dataDAO = dataDAO;
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

            Data tableData = new Data();
            tableData.setSum(sum);
            tableData.setFile(arg);
            dataDAO.create(tableData);


        } catch (IOException e) {
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
