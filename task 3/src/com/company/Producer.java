package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable{

    protected BlockingQueue<String> blockingQueue = null;

    public Producer(BlockingQueue<String> blockingQueue){
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        BufferedReader br = null;
        try {
            File file = new File("inputFile.txt");
            br = new BufferedReader(new FileReader(file));
            System.out.println("The file has been opened successfully!");

            String buffer = null;

            while((buffer = br.readLine()) != null)
            {
                blockingQueue.put(buffer);
            }
            blockingQueue.put("EOF");  //end of file has been reached

        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        } catch(InterruptedException e){

        }finally{
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }



}
