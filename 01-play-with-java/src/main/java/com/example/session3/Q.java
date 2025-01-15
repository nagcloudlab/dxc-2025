package com.example.session3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

// collection = data structure + algorithm
// data structure + iterator = iterable
// iterator => helps to iterate/loop over a collection

class MyList<E> implements Iterable<E> {

    private E[] elements;
    private int size;

    public MyList(int initialCapacity) {
        this.elements = (E[]) new Object[initialCapacity];
        this.size = 0;
    }

    public void add(E e) {
        elements[size] = e;
        size++;
    }

    public E get(int index) {
        return elements[index];
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {

        return new Iterator<E>() {

            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public E next() {
                return elements[index++];
            }
        };

    }

}

public class Q {

    public static void main(String[] args) {

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("A");
        arrayList.add("B");

        for (String s : arrayList) {
            System.out.println(s);
        }

        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("A");
        linkedList.add("B");

        for (String s : linkedList) {
            System.out.println(s);
        }

        MyList<String> myList = new MyList<>(10);
        myList.add("A");
        myList.add("B");

        for (String s : myList) {
            System.out.println(s);
        }

    }

}
