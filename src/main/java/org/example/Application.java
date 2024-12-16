package org.example;

import org.example.entity.Car;
import org.example.entity.CarShowroom;
import org.example.entity.Category;
import org.example.entity.Client;
import org.example.entity.Review;
import org.example.garbage.util.HibernateUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
//        populateDatabase();
    }


//    public static void populateDatabase() {
//        try (var session = HibernateUtil.getSession()) {
//            session.beginTransaction();
//
//            Category sedanCategory = new Category();
//            sedanCategory.setName("Sedan");
//
//            Category suvCategory = new Category();
//            suvCategory.setName("SUV");
//
//            session.persist(sedanCategory);
//            session.persist(suvCategory);
//
//            CarShowroom showroom1 = new CarShowroom();
//            showroom1.setName("Luxury Cars");
//            showroom1.setAddress("123 Main Street");
//
//            CarShowroom showroom2 = new CarShowroom();
//            showroom2.setName("Family Cars");
//            showroom2.setAddress("456 Elm Street");
//
//            session.persist(showroom1);
//            session.persist(showroom2);
//
//            Car car1 = new Car();
//            car1.setBrand("BMW");
//            car1.setModel("X5");
//            car1.setYear(2021);
//            car1.setPrice(75000);
//            car1.setCategory(suvCategory);
//            car1.setCarShowroom(showroom1);
//
//            Car car2 = new Car();
//            car2.setBrand("Toyota");
//            car2.setModel("Camry");
//            car2.setYear(2020);
//            car2.setPrice(30000);
//            car2.setCategory(sedanCategory);
//            car2.setCarShowroom(showroom2);
//
//            session.persist(car1);
//            session.persist(car2);
//
//            Client client1 = new Client();
//            client1.setName("Alice");
//            client1.setRegistrationDate(new Date());
//            client1.getContacts().add("alice@example.com");
//            client1.getContacts().add("+123456789");
//            client1.getCars().add(car1);
//
//            Client client2 = new Client();
//            client2.setName("Bob");
//            client2.setRegistrationDate(new Date());
//            client2.getContacts().add("bob@example.com");
//            client2.getContacts().add("+987654321");
//            client2.getCars().add(car2);
//
//            session.persist(client1);
//            session.persist(client2);
//
//            Review review1 = new Review();
//            review1.setText("Great SUV, very comfortable!");
//            review1.setRating(5);
//            review1.setClient(client1);
//            review1.setCar(car1);
//
//            Review review2 = new Review();
//            review2.setText("Affordable and reliable sedan.");
//            review2.setRating(4);
//            review2.setClient(client2);
//            review2.setCar(car2);
//            session.persist(review1);
//            session.persist(review2);
//
//            session.getTransaction().commit();
//        }
//    }
}
