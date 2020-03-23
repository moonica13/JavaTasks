package com.company;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

//launcher class

public class Main {

    public static void main(String[] args) {

        int MAX_CAPACITY = 1000000;

        BlockingQueue<String> queue = new ArrayBlockingQueue<>(MAX_CAPACITY);

        Producer reader = new Producer(queue);
        Consumer writer = new Consumer(queue);

        new Thread(reader).start();
        new Thread(writer).start();

    }

}