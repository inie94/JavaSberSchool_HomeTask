package ru.anani.lesson2.carspark;

import java.util.*;

public class Solution {
    public static void main(String[] args) {

        List<Car> park = new ArrayList<>();

        Car car1 = new Car("Lada", "sedan");
        Car car2 = new Car("Lada", "hatchback");
        Car car3 = new Car("Mercedes", "sedan");
        Car car4 = new Car("Mercedes", "hatchback");
        Car car5 = new Car("BMW", "sedan");
        Car car6 = new Car("BMW", "hatchback");
        Car car7 = new Car("Ford", "sedan");
        Car car8 = new Car("Ford", "crossover");
        Car car9 = new Car("Peugeot", "sedan");
        Car car10 = new Car("Peugeot", "hatchback");
        Car car11 = new Car("BMW", "crossover");
        Car car12 = new Car("Mercedes", "crossover");

        park.add(car1);
        park.add(car2);
        park.add(car3);
        park.add(car4);
        park.add(car5);
        park.add(car6);
        park.add(car7);
        park.add(car8);
        park.add(car9);
        park.add(car10);
        park.add(car11);
        park.add(car12);

        Map<String, List<Car>> carMap = listsOfCarModelsByType(park);

        carMap.forEach((s, cars) -> {
                System.out.println(s.toUpperCase(Locale.ROOT) + ":");
                cars.forEach(System.out::println);
                System.out.println();
            }
        );

    }

    public static Map<String, List<Car>> listsOfCarModelsByType(List<Car> carsPark) {
        Set<String> typeSet = new HashSet<>();

        carsPark.forEach(car -> typeSet.add(car.getType()));

        Map<String, List<Car>> carMap = new HashMap<>();

        typeSet.forEach(type -> {
            List<Car> carList = new ArrayList<>();
            carsPark.stream().filter(car -> car.getType().equals(type)).forEach(carList::add);
            carMap.put(type, carList);
        });

        return carMap;
    }
}
