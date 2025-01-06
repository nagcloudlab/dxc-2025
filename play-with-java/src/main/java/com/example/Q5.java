package com.example;


/*

    Choosing Functional Interface

        single/no parameter

		nothing->T            Supplier
		T->nothing            Consumer
		T->boolean            Predicate
		S->T                  Function

		2 parameters ( bi ) parameter

		T, U->nothing         BiConsumer
		T, U->T               BiFunction
		T, U->boolean         BiPredicate


		input & output are same type

		nothing->nothing      Runnable
		T->T                  UnaryOperator
		T1, T2->T             BinaryOperator


 */


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.*;

public class Q5 {
    public static void main(String[] args) {


        Supplier<String> supplier = () -> "Hello World";
        Consumer<String> consumer = (s) -> System.out.println(s);
        Predicate<String> predicate = (s) -> s.length() > 5;
        Function<String, Integer> function = (s) -> s.length();


        BiConsumer<String, String> biConsumer = (s1, s2) -> System.out.println(s1 + s2);
        BiFunction<String, String, Integer> biFunction = (s1, s2) -> s1.length() + s2.length();
        BiPredicate<String, String> biPredicate = (s1, s2) -> s1.length() > s2.length();

        Runnable runnable = () -> System.out.println("Hello World");
        UnaryOperator<String> unaryOperator = (s) -> s + " World";
        BinaryOperator<String> binaryOperator = (s1, s2) -> s1 + s2;


        BiFunction<Integer,Integer,Integer> add1 = (a,b)->a+b;
        BinaryOperator<Integer> add2 = (a,b)->a+b;

        System.out.println(add1.apply(10,20)); // int -> Integer -> int -> Integer
        System.out.println(add2.apply(10,20));


        // primitive version of functional interface

        IntBinaryOperator intBinaryOperator = (a,b)->a+b; // int -> int -> int
        LongBinaryOperator longBinaryOperator = (a,b)->a+b; // long -> long -> long
        DoubleBinaryOperator doubleBinaryOperator = (a,b)->a+b; // double -> double -> double


        IntSupplier intSupplier = ()->10;
        LongSupplier longSupplier = ()->10L;
        DoubleSupplier doubleSupplier = ()->10.0;


        IntToDoubleFunction intToDoubleFunction = (a)->a;


        List<String> menu=new ArrayList<>();
        menu.add("veg");
        menu.add("non-veg");
        menu.add("veg");
        menu.add("non-veg");
        menu.add("veg");

        // remove all non-veg items from menu

        // style-1: imperative style

//        Iterator<String> iterator = menu.iterator();
//        while(iterator.hasNext()){
//            String item = iterator.next();
//            if(item.equals("non-veg")){
//                iterator.remove();
//            }
//        }
//
//        System.out.println(menu);

        // style-2: declarative style


        // mutable
//        menu.removeIf(item->item.equals("non-veg"));
//        System.out.println(menu);

//
//        menu.replaceAll(item->item.equals("non-veg")?"veg":item);
//        System.out.println(menu);




    }
}
