package com.example.session1;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Dish{
    private String name;
    private int calories;
    private boolean isVegetarian;
    private Type type;

    public Dish(String name, int calories, boolean isVegetarian, Type type) {
        this.name = name;
        this.calories = calories;
        this.isVegetarian = isVegetarian;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getCalories() {
        return calories;
    }

    public boolean isVegetarian() {
        return isVegetarian;
    }

    public Type getType() {
        return type;
    }

    public enum Type{
        MEAT, FISH, OTHER
    }


    public static List<Dish> menu = Arrays.asList(
            new Dish("pork", 800, false, Type.MEAT),
            new Dish("beef", 700, false, Type.MEAT),
            new Dish("chicken", 400, false, Type.MEAT),
            new Dish("french fries", 530, true, Type.OTHER),
            new Dish("rice", 350, true, Type.OTHER),
            new Dish("season fruit", 120, true, Type.OTHER),
            new Dish("pizza", 550, true, Type.OTHER),
            new Dish("prawns", 300, false, Type.FISH),
            new Dish("salmon", 450, false, Type.FISH)
    );

}


public class Q4 {
    public static void main(String[] args) {

        List<Dish> menu= Dish.menu; // fetch data from file or database or any other source

        // data processing
        // e.g
        // - filtering
        // - transformation
        // - slicing
        // - min/max/count
        // - grouping
        // - joining
        // - soring
        // - partitioning

        // Customer Req: get low calorie dish's names from menu sorted by calories

        // till Java 1.7 version

        System.out.println(
                getLowCalorieDishNamesV1(menu)
        );

        // Java 1.8 version

        System.out.println(
                getLowCalorieDishNamesV2(menu)
        );

    }


    // Declarative style
    // - LE with streams api => data processing pipeline ( on single thread  / multiple threads )
    private static List<String> getLowCalorieDishNamesV2(List<Dish> menu){
        return menu
                .parallelStream() // ForkJoinPool
                .filter(dish -> dish.getCalories() < 400)
                .sorted(Comparator.comparing(Dish::getCalories)) // method reference
                .map(Dish::getName) // method reference
                .collect(Collectors.toList());
    }


    // Imperative style

    // issues

    // - more code
    // - intention & implementation are mixed
    // - sequential
    // - on multiple threads , data-races can occur
    // - to prevent race conditions, we need to use locks, which makes code more complex ( slow )


    private static List<String> getLowCalorieDishNamesV1(List<Dish> menu){

        // data processing pipeline

        // step-1: filter low calorie dishes
        List<Dish> lowCalorieDishes = new ArrayList<>();
        for(Dish dish: menu){
            if(dish.getCalories() < 400){
                lowCalorieDishes.add(dish);
            }
        }
        // step-2: sort low calorie dishes
        lowCalorieDishes.sort(new Comparator<Dish>() {
            @Override
            public int compare(Dish o1, Dish o2) {
                return Integer.compare(o1.getCalories(), o2.getCalories());
            }
        });
        // step-3: get dish names
        List<String> lowCalorieDishNames = new ArrayList<>();
        for(Dish dish: lowCalorieDishes){
            lowCalorieDishNames.add(dish.getName());
        }

        return lowCalorieDishNames;

    }

}
