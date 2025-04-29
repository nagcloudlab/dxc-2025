package com.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Example2 {
    public static void main(String[] args) {

         ExecutorService executorService = Executors.newFixedThreadPool(3);
        // ExecutorService executorService = Executors.newSingleThreadExecutor();
        // ExecutorService executorService = Executors.newCachedThreadPool();
        // ExecutorService executorService = Executors.newScheduledThreadPool(3);

        Runnable task = () -> {
            System.out.println("Task is running in thread: " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000); // Simulate some work
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Task was interrupted");
            }
            System.out.println("Task completed in thread: " + Thread.currentThread().getName());
        };

        // Submit the task to the executor
        executorService.submit(task);
        executorService.submit(task); // This will be queued until the first task is completed
        executorService.submit(task); // This will be queued until the first task is completed

    }
}
