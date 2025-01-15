package com.example.session1;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class URLClassLoaderExample {
    public static void main(String[] args) {
        // Path to the JAR file
        String jarPath = "/Users/nag/dxc-2025/math-lib/target/math-lib-1.0-SNAPSHOT.jar";

        try {

            // Create a URL for the JAR file
            URL jarUrl = new URL("file:" + jarPath);

            // Create a URLClassLoader
            try (URLClassLoader loader = new URLClassLoader(new URL[]{jarUrl})) {

                // Reflection

                // Load the class from the JAR file
                Class<?> clazz = loader.loadClass("com.example.Arith");

                // Create an instance of the loaded class
                Object instance = clazz.getDeclaredConstructor().newInstance();

                // Invoke the method "sayHello"
                Method[] methods = clazz.getMethods();
                for (Method method : methods) {
                    //System.out.println("Method Name: " + method.getName());
                    if (method.getName().equals("add")) {
                        // Invoke the method
                        Object result=method.invoke(instance,new Object[]{10,20});
                        System.out.println("Result is : "+result); // 30
                    }
                }


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
