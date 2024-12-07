package org.example;

import org.example.entity.Car;
import org.example.entity.CarShowroom;
import org.example.entity.Category;
import org.example.entity.Client;
import org.example.entity.Review;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
        SessionFactory sessionFactory = new Configuration()
                .addAnnotatedClass(Car.class)
                .addAnnotatedClass(CarShowroom.class)
                .addAnnotatedClass(Client.class)
                .addAnnotatedClass(Category.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();

    }
}