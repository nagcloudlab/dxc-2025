package com.example;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class Example3 {
    public static void main(String[] args) {


        Callable<List<String>> readFile1 = () -> {
            // Simulate a long-running task
            Thread.sleep(2000);
            Scanner scanner = new Scanner(new File("/Users/nag/dxc-2025/concurrency/file1.txt"));
            List<String> lines = new ArrayList<>();
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
            scanner.close();
            return lines;
        };


        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<List<String>> future = executorService.submit(readFile1);
        System.out.println("File is being read in the background...");
        //......
        try {
            System.out.println("Waiting for the file to be read...");
            List<String> lines = future.get(); // This will block until the file is read
            System.out.println("File read successfully");
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }


    }
}
