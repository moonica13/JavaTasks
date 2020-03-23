package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Consumer implements Runnable {

    protected BlockingQueue<String> blockingQueue = null;
    final int comp = 100;

    public Consumer(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        PrintWriter writer = null;

        try {
            writer = new PrintWriter(new File("outputFile.txt"));

            while (true) {
                String buffer = blockingQueue.poll(10, TimeUnit.SECONDS);

                if (buffer == null){
                    break;
                }

                //Check whether end of file has been reached
                if (buffer.equals("EOF")) break;

                try {
                    int parsedBuffer = Integer.parseInt(buffer);


                    if (Integer.compare(parsedBuffer, comp) == -1)
                        writer.println(parsedBuffer + 1);
                    else if (Integer.compare(parsedBuffer, comp) == 1)
                        writer.println(parsedBuffer - 1);
                    else
                        writer.println(parsedBuffer);

                } catch (NumberFormatException e) {
                    System.out.println( "\"" +  buffer + "\"" + ": Oopsie, that's not a number, pal!");
                }

            }

        } catch (FileNotFoundException | InterruptedException e) {
            e.printStackTrace();

        } finally {
            if (writer != null)
                 writer.close();
        }

    }

}