package org.example.util;

import lombok.Getter;
import lombok.experimental.UtilityClass;
import org.example.entity.Car;
import org.example.entity.CarShowroom;
import org.example.entity.Category;
import org.example.entity.Client;
import org.example.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

@UtilityClass
public class HibernateUtil {

    @Getter private static final SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration()
                    .addAnnotatedClass(Car.class)
                    .addAnnotatedClass(CarShowroom.class)
                    .addAnnotatedClass(Client.class)
                    .addAnnotatedClass(Category.class)
                    .addAnnotatedClass(Review.class)
                    .buildSessionFactory();
        } catch (Exception ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }

    public static void shutdown() {
        getSessionFactory().close();
    }

    public static void dropAllTables() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            String sql = "DROP SCHEMA public CASCADE; CREATE SCHEMA public;";
            Query query = session.createNativeQuery(sql);
            query.executeUpdate();
            transaction.commit();
            System.out.println("All tables dropped successfully.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        HibernateUtil.shutdown();
    }
}
