package com.example.session3;


/*

    Person
     Has Car ( optional )
        Car has Insurance ( optional )

 */

//class Person {
//    Car car;
//}
//
//class Car {
//    Insurance insurance;
//}
//
//class Insurance {
//    String name;
//}


import java.util.Optional;

class Person {
    Optional<Car> car= Optional.empty();
    public Optional<Car> getCar() {
        return car;
    }
}

class Car {
    Optional<Insurance> insurance= Optional.empty();
    public Optional<Insurance> getInsurance() {
        return insurance;
    }
}

class Insurance {
    String name;
    public String getName() {
        return name;
    }
}

public class OptionalApiEx {
    public static void main(String[] args) {

        // scenario 1: Person has car and car has insurance
//        Person person = new Person();
//        Car car = new Car();
//        Insurance insurance = new Insurance();
//        insurance.name = "My Insurance";
//        car.insurance = insurance;
//        person.car = car;
//
//        String insuranceName= getInsuranceName(person);
//        System.out.println(insuranceName);


        // scenario 2: Person has car but car does not have insurance
//        Person person2 = new Person();
//        Car car2 = new Car();
//        person2.car = car2;
//
//        String insuranceName2= getInsuranceName(person2);
//        System.out.println(insuranceName2);


        //-------------------------------------------

        // scenario 1: Person has car and car has insurance
        Person person = new Person();
        Car car = new Car();
        Insurance insurance = new Insurance();
        insurance.name = "My Insurance";

        car.insurance = Optional.of(insurance);
        person.car = Optional.of(car);

        String insuranceName= getInsuranceName(person);
        System.out.println(insuranceName);

        // scenario 2: Person has car but car does not have insurance

        Person person2 = new Person();
        Car car2 = new Car();
        person2.car = Optional.of(car2);

        String insuranceName2= getInsuranceName(person2);
        System.out.println(insuranceName2);



    }

    public static String getInsuranceName(Person person) {
        return person.car.flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("No Insurance");
    }


//    public static String getInsuranceName(Person person){
//        //return person.car.insurance.name;
//        if(person!=null){
//            Car car = person.car;
//            if(car!=null){
//                Insurance insurance = car.insurance;
//                if(insurance!=null){
//                    return insurance.name;
//                }
//            }
//        }
//        return "No Insurance";
//    }

}
