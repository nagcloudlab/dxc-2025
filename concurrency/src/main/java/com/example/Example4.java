package com.example;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

public class Example4 {
    public static void main(String[] args) {

        // get user details  ( async )
        // get user's order details ( async )
        // for 2 users ( foo, bar )

        ExecutorService pool1 = java.util.concurrent.Executors.newFixedThreadPool(2);
        ExecutorService pool2 = java.util.concurrent.Executors.newFixedThreadPool(2);
        ExecutorService pool3 = java.util.concurrent.Executors.newFixedThreadPool(2);

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("Fetching user1 details");
            // Simulate a delay
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "User1";
        }, pool1);

        completableFuture = completableFuture.thenApplyAsync(user -> {
            System.out.println("Fetching order details for " + user);
            // Simulate a delay
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Order details for " + user;
        }, pool2);

        completableFuture.thenAcceptAsync(orderDetails -> {
            System.out.println("Order details fetched: " + orderDetails);
        }, pool3);

    }
}
