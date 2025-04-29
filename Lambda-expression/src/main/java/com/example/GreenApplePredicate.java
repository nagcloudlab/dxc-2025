package com.example;

import com.example.model.Apple;

import java.util.function.Predicate;

public class GreenApplePredicate implements Predicate<Apple> {
    @Override
    public boolean test(Apple apple) {
        return "green".equals(apple.getColor());
    }
}
