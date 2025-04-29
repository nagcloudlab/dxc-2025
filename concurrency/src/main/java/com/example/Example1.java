package com.example;

// s/w
// 2 types of tasks
// 1. CPU bound
// 2. IO bound

import java.util.Scanner;

public class Example1 {
    public static void main(String[] args) {

        String threadName = Thread.currentThread().getName();

        Thread thread1 = new Thread(() -> {
            ioTask();
        }, "T1");

        Thread thread2 = new Thread(() -> {
            compute();
        }, "T2");

        thread1.start(); // allocate stack space for thread 1
        thread2.start(); // allocate stack space for thread 2


    }

    private static void compute() {
        // Simulate a CPU-bound task
        System.out.println("Task 1 started by " + Thread.currentThread().getName());
        for (int i = 0; i < 10000000000L; i++) {
            // Simulating some computation
            double result = Math.sqrt(i);
        }
        System.out.println("Task 1 completed by " + Thread.currentThread().getName());
    }

    private static void ioTask() {
        // Simulate an IO-bound task
        System.out.println("Task 2 started by " + Thread.currentThread().getName());
        // Simulating some IO operation
        System.out.println("Reading data from a std input stream...");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println("Data read: " + input);
        System.out.println("Task 2 completed by " + Thread.currentThread().getName());
    }

}
