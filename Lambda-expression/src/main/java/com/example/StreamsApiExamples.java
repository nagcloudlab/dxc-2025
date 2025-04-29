package com.example;

import com.example.model.Dish;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import static java.util.stream.Collectors.*;

public class StreamsApiExamples {

    public static void main(String[] args) {

        List<Dish> menu = Dish.menu;

        // Req-1: group the dishes by type
        Map<Dish.Type, List<Dish>> dishesByType = menu
                .stream()
                .collect(groupingBy(Dish::getType));
        System.out.println(dishesByType);

        // Req-2: group the dishes by type and count them
        Map<Dish.Type, Long> dishCountByType = menu
                .stream()
                .collect(groupingBy(Dish::getType, counting()));
        System.out.println(dishCountByType);

        // Req-3: group the dishes by type and calculate the sum of their calories
        Map<Dish.Type, Integer> totalCaloriesByType = menu
                .stream()
                .collect(groupingBy(Dish::getType, summingInt(Dish::getCalories)));
        System.out.println(totalCaloriesByType);

        // Req-4: group the dishes by type and calculate the Statistics of their calories
        Map<Dish.Type, IntSummaryStatistics> caloriesStatsByType = menu
                .stream()
                .collect(groupingBy(Dish::getType, summarizingInt(Dish::getCalories)));
        System.out.println(caloriesStatsByType);


        // Partitioning the dishes by vegetarian and non-vegetarian
        Map<Boolean, List<Dish>> partitionedDishes = menu
                .stream()
                .collect(partitioningBy(Dish::isVegetarian));
        System.out.println(partitionedDishes);

        // is every dish vegetarian?
        boolean allVegetarian = menu
                .stream()
                .allMatch(Dish::isVegetarian);
        System.out.println("Are all dishes vegetarian? " + allVegetarian);

    }

}
