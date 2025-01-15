package com.example.session3;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Employee {
    private String name;
    private int age;
    private double salary;

    public Employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }

}


public class StreamsApiEx {

    public static void main(String[] args) {


        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("A", 23, 1000));
        employees.add(new Employee("B", 33, 4000));
        employees.add(new Employee("C", 43, 2000));
        employees.add(new Employee("D", 53, 1000));
        employees.add(new Employee("E", 63, 7000));

        // sorting all employees by salary
        employees.sort((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));


        // need all employees whose salary is less than 4000
        List<Employee> filteredEmployees= employees
                .stream()
                //.filter(e -> e.getSalary() < 4000)
                //.takeWhile(e -> e.getSalary() < 4000)
                .dropWhile(e -> e.getSalary() < 4000)
                .collect(Collectors.toList());

        System.out.println(filteredEmployees);


    }

}
