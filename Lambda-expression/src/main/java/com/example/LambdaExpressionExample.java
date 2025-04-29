package com.example;

import com.example.model.Apple;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class LambdaExpressionExample {
    public static void main(String[] args) {

        List<Apple> appleInventory = List.of(
                new Apple("green", 150),
                new Apple("red", 120),
                new Apple("yellow", 130),
                new Apple("green", 160),
                new Apple("red", 140)
        );

        // Req-1 : filter green apples
        System.out.println(
                //filterGreenApples(appleInventory)
                //filterApplesByColor(appleInventory, "green")
                filterApples(appleInventory, new GreenApplePredicate())

        );

        // Req-2 : filter red apples
        System.out.println(
                //filterRedApples(appleInventory)
                //filterApplesByColor(appleInventory, "red")
                filterApples(appleInventory, new Predicate<Apple>() {
                    @Override
                    public boolean test(Apple apple) {
                        return "red".equals(apple.getColor());
                    }
                })
        );

        // Req-3 : filter apples by weight ( > 150)
        System.out.println(
                //filterApplesByWeight(appleInventory, 150)
                filterApples(appleInventory, apple -> apple.getWeight() > 150) // lambda expression
        );


    }


    // why we functional style code
    // to write compact code / concise code
    // to write reusable code ( behavior parameterization )
    // lazy evaluation ( deferred execution )
    // to write parallel code ( multi-threading )
    // for distributed computing ( cloud computing )


    // style of coding : declarative style
    // solve problem using high level abstraction

    // intention separated from implementation
    // how?
    // by value parameter
    // by object parameter
    // by lambda expression ( function ) -> Functional Programming


    // by object/function parameter
    private static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> predicate) {
        List<Apple> filteredApples = new ArrayList<>();
        for (Apple a : inventory) {
            if (predicate.test(a)) {
                filteredApples.add(a);
            }
        }
        return filteredApples;
    }

    // by value parameter
    private static List<Apple> filterApplesByColor(List<Apple> inventory, String color) {
        List<Apple> filteredApples = new ArrayList<>();
        for (Apple apple : inventory) {
            if (color.equals(apple.getColor())) {
                filteredApples.add(apple);
            }
        }
        return filteredApples;
    }

    private static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight) {
        List<Apple> filteredApples = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getWeight() > weight) {
                filteredApples.add(apple);
            }
        }
        return filteredApples;
    }


    // style of coding : imperative style
    // solve problem using step-by-step approach

    // intention  + implementation mixed
    private static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> greenApples = new ArrayList<>();
        for (Apple apple : inventory) {
            if ("green".equals(apple.getColor())) {
                greenApples.add(apple);
            }
        }
        return greenApples;
    }

    private static List<Apple> filterRedApples(List<Apple> inventory) {
        List<Apple> redApples = new ArrayList<>();
        for (Apple apple : inventory) {
            if ("red".equals(apple.getColor())) {
                redApples.add(apple);
            }
        }
        return redApples;

    }

}
