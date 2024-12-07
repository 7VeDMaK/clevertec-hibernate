package org.example;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
        SessionFactory sessionFactory = new Configuration()
                .addAnnotatedClass(org.example.entity.Car.class)
                .buildSessionFactory();

//        var session = HibernateUtil.getSession();
//        session.beginTransaction();
//        var query = session.createQuery("FROM Car", Car.class);
//        session.getTransaction().commit();
    }
}