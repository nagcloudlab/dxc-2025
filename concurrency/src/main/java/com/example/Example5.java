package com.example;

import java.util.concurrent.ExecutorService;


class Counter {
    private int count = 0;

    public synchronized void increment() {
        count++;  // This is not thread-safe
    }

    public int getCount() {
        return count;
    }
}

public class Example5 {
    public static void main(String[] args) throws InterruptedException {


        ExecutorService executorService = java.util.concurrent.Executors.newFixedThreadPool(1000);

        Counter counter = new Counter();

        for (int i = 0; i < 1000; i++) {
            executorService.execute(() -> {
                for (int j = 0; j < 1000; j++) {
                    counter.increment();
                }
            });
        }

        Thread.sleep(3 * 1000); // Wait for all tasks to finish

        System.out.println("Final count: " + counter.getCount()); // 1000 * 1000 = 1000000

    }
}
