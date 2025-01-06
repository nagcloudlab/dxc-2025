package com.example;

// Why we need functional programming in Java
// How to write functional programming in Java


import java.util.ArrayList;
import java.util.List;

class Apple{
    private String color;
    private int weight;
    public Apple(String color, int weight) {
        this.color = color;
        this.weight = weight;
    }
    public String getColor() {
        return color;
    }
    public int getWeight() {
        return weight;
    }
    @Override
    public String toString() {
        return "Apple{" +
                "color='" + color + '\'' +
                ", weight=" + weight +
                '}';
    }
}



/*

// Function as a first class citizen

    ApplePredicate greenApplePredicate=(Apple apple) -> {
                    return "Green".equals(apple.getColor());
                }

 */


public class Q3 {
    public static void main(String[] args) {

        List<Apple> inventory=List.of(
                new Apple("Red", 100),
                new Apple("Green", 200),
                new Apple("Red", 150));


        // - filter requirements

        // customer - Req-1: I want to filter out all the red apples

        System.out.println(
            //filterRedApples(inventory)
            //filterApplesByColor(inventory, "Red")
            filterApples(inventory,new RedApplePredicate())
        );

        // customer - Req-2: I want to filter out all the green apples
        System.out.println(
            // filterGreenApples(inventory)
            //filterApplesByColor(inventory, "Green")
            filterApples(inventory, new ApplePredicate() {
                @Override
                public boolean test(Apple apple) {
                    return "Green".equals(apple.getColor());
                }
            })
        );

        // customer - Req-3: I want to filter out all the apples which are heavier than 150

        System.out.println(
            //filterApplesByWeight(inventory, 150)
            filterApples(inventory,apple-> apple.getWeight() > 150) // concise code
        );


        // In java, to create a function / Lambda expression, we need to have a functional interface
        // Any interface with only one abstract method is called as functional interface
        // SAM - Single Abstract Method

        // e.g javascript
        // function(a,b){}
        // e.g python
        // def function(a,b): pass

        // LE is an implementation of the functional interface

        ApplePredicate greenApplePredicate=(Apple apple) -> {
            return "Green".equals(apple.getColor());
        };

        ApplePredicate heavyApplePredicate=(Apple apple) -> {
            return apple.getWeight() > 150;
        };

        // customer - Req-4: I want to filter out all the green apples which are heavier than 150

        System.out.println(
            filterApples(inventory, greenApplePredicate.and(heavyApplePredicate))
        );

        // customer - Req-5: I want to filter out all the apples which are not red

        ApplePredicate redApplePredicate=(apple) -> "Red".equals(apple.getColor());
        ApplePredicate notRedApplePredicate=ApplePredicate.negate(redApplePredicate);

        System.out.println(
            filterApples(inventory, notRedApplePredicate)
        );



    }


    // - declarative style of programming
    // - separating intention & implementation
    // - how ?
    // - by primitive params
    // - by object params
    // - by function params ( function as a first class citizen) - behavior parameterization => FP

    // types ( interface | class | enum | annotation)

    // - interface

    interface ApplePredicate{
        boolean test(Apple apple); // One Abstract method
        // many default & static methods

        // default method ( e.g instance method)
        default ApplePredicate and(ApplePredicate other){
            return (Apple apple) -> test(apple) && other.test(apple);
        }

        // static method ( e.g class method)
        static ApplePredicate negate(ApplePredicate other){
            return (Apple apple) -> !other.test(apple);
        }

    }

    static class RedApplePredicate implements ApplePredicate{
        @Override
        public boolean test(Apple apple) {
            return "Red".equals(apple.getColor());
        }
    }

    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate predicate){
        List<Apple> result= new ArrayList<>();
        for(Apple apple: inventory){
            if(predicate.test(apple)){
                result.add(apple);
            }
        }
        return result;
    }


    public static List<Apple> filterApplesByColor(List<Apple> inventory, String color){
        List<Apple> result= new ArrayList<>();
        for(Apple apple: inventory){
            if(color.equals(apple.getColor())){
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight){
        List<Apple> result= new ArrayList<>();
        for(Apple apple: inventory){
            if(apple.getWeight() > weight){
                result.add(apple);
            }
        }
        return result;
    }


    // - imperative style of programming
    // - solving any problem using step by step approach ( intention & implementation mixed together)
    public static List<Apple> filterRedApples(List<Apple> inventory){
        List<Apple> result= new ArrayList<>();
        for(Apple apple: inventory){
            if("Red".equals(apple.getColor())){
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterGreenApples(List<Apple> inventory){
        List<Apple> result= new ArrayList<>();
        for(Apple apple: inventory){
            if("Green".equals(apple.getColor())){
                result.add(apple);
            }
        }
        return result;
    }

}
