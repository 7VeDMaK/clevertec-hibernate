package org.example.service;

import org.example.entity.Car;
import org.example.entity.CarShowroom;
import org.example.entity.Category;
import org.example.entity.Review;

import java.util.Arrays;
import java.util.List;

public class CarTestData {

    public static Car createTestCar1() {
        Car car = new Car();
        car.setId(1L);
        car.setModel("Model S");
        car.setBrand("Tesla");
        car.setYear(2022);
        car.setPrice(79999.99);

        Category category = new Category();
        category.setId(1L);
        category.setName("Electric");

        CarShowroom showroom = new CarShowroom();
        showroom.setId(1L);
        showroom.setName("Elite Cars");
        showroom.setAddress("123 Main Street");

        car.setCategory(category);
        car.setCarShowroom(showroom);

        Review review1 = new Review(1L, "Great car!", 5, null, null);
        Review review2 = new Review(2L, "Not worth the price.", 3, null, null);

        car.setReviews(Arrays.asList(review1, review2));

        return car;
    }

    public static Car createTestCar2() {
        Car car = new Car();
        car.setId(2L);
        car.setModel("Mustang");
        car.setBrand("Ford");
        car.setYear(2020);
        car.setPrice(55999.99);

        Category category = new Category();
        category.setId(2L);
        category.setName("Sports");

        CarShowroom showroom = new CarShowroom();
        showroom.setId(2L);
        showroom.setName("Fast Wheels");
        showroom.setAddress("456 Elm Street");

        car.setCategory(category);
        car.setCarShowroom(showroom);

        Review review1 = new Review(3L, "Amazing performance!", 5, null, null);

        car.setReviews(List.of(review1));

        return car;
    }
}

