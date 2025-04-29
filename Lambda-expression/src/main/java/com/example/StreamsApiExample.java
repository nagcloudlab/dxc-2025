package com.example;

import com.example.model.Dish;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;



// imperative style
// intention + implementation mixed
// too much boilerplate code
// sequential ( only one thread)
// too many mutations
// hard debugging & refactoring

// data processing
// - filtering
// - sorting
// - mapping / transforming
// - reducing
// - max / min / sum / average
// - grouping
// - partitioning
// - allMatch / anyMatch / noneMatch
// - findFirst / findAny
// ...

public class StreamsApiExample {

    public static void main(String[] args) {

        List<Dish> menu = Dish.menu;
        // Req-1 : get low calorie dish names ( calories < 400) ), sorted by calories

        System.out.println(
                getLowCalorieDishNames_v1(menu)
        );

        System.out.println(
                getLowCalorieDishNames_v2(menu)
        );

        System.out.println(
                getLowCalorieDishNames_v3(menu)
        );

    }

    private static List<String> getLowCalorieDishNames_v3(List<Dish> menu) {
        return menu.stream()
                .parallel() // parallel stream ( use multiple threads ) // pool -> ForkJoinPool
                .filter(dish -> dish.getCalories()<400)
                .sorted(Comparator.comparing(Dish::getCalories)) // method reference
                .map(Dish::getName) // method reference
                .collect(Collectors.toList());
    }


    private static List<String> getLowCalorieDishNames_v2(List<Dish> menu) {
        return menu.stream()
                .parallel() // parallel stream ( use multiple threads ) // pool -> ForkJoinPool
                .filter(dish -> dish.getCalories()<400)
                .sorted((d1, d2) -> Integer.compare(d1.getCalories(), d2.getCalories()))
                .map(d->d.getName())
                .collect(Collectors.toList());
    }


    private static List<String> getLowCalorieDishNames_v1(List<Dish> menu) {

        // step 1: filter ( calories < 400)
        List<Dish> filteredMenu = new ArrayList<>();
        for (Dish dish : menu) {
            if (dish.getCalories() < 400) {
                filteredMenu.add(dish);
            }
        }
        // step 2: sort
        filteredMenu.sort(new Comparator<Dish>() {
            @Override
            public int compare(Dish o1, Dish o2) {
                return Integer.compare(o1.getCalories(), o2.getCalories());
            }
        });
        // step 3: map
        List<String> dishNames = new ArrayList<>();
        for (Dish dish : filteredMenu) {
            dishNames.add(dish.getName());
        }

        return dishNames;

    }

}
