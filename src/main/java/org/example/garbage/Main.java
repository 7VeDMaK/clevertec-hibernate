//package org.example;
//
//import org.example.entity.Car;
//import org.example.entity.CarShowroom;
//import org.example.entity.Category;
//import org.example.entity.Client;
//import org.example.entity.Review;
//import org.example.util.HibernateUtil;
//
//import java.util.Date;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//public class Main {
//    public static void main(String[] args) {
//        Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
////        HibernateUtil.dropAllTables();
////        populateDatabase();
//        System.out.println(getCars());
//
//    }
//
//    public static List<Car> getCars() {
//        try (var session = HibernateUtil.getSession()) {
//            session.beginTransaction();
//            var query = session.createQuery("FROM Car", Car.class);
//            List<Car> cars = query.list();
//
//            session.getTransaction().commit();
//            return cars;
//        }
//    }
//
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
//
//
////    public static void getPostCriteria() {
////        try (Session session = HibernateUtil.getSession()) {
////            // Запрос 1: Извлечение всех постов
////            CriteriaBuilder cb = session.getCriteriaBuilder();
////            CriteriaQuery<Post> cq1 = cb.createQuery(Post.class);
////            Root<Post> root1 = cq1.from(Post.class);
////            cq1.select(root1);
////            Query<Post> query1 = session.createQuery(cq1);
////            List<Post> posts = query1.getResultList();
////            posts.forEach(System.out::println);
////
////            // Запрос 2: Извлечение постов по имени автора
////            CriteriaQuery<Post> cq2 = cb.createQuery(Post.class);
////            Root<Post> root2 = cq2.from(Post.class);
////            cq2.select(root2).where(cb.equal(root2.get("author").get("name"), "Alice"));
////            Query<Post> query2 = session.createQuery(cq2);
////            List<Post> postsByAuthor = query2.getResultList();
////            postsByAuthor.forEach(System.out::println);
////
////            // Запрос 3: Извлечение комментариев для определенного поста
////            CriteriaQuery<PostComment> cq3 = cb.createQuery(PostComment.class);
////            Root<PostComment> root3 = cq3.from(PostComment.class);
////            cq3.select(root3).where(cb.equal(root3.get("post").get("id"), 1L));
////            Query<PostComment> query3 = session.createQuery(cq3);
////            List<PostComment> comments = query3.getResultList();
////            comments.forEach(System.out::println);
////
////            // Запрос 4: Извлечение постов с их комментариями
////            CriteriaQuery<Post> cq4 = cb.createQuery(Post.class);
////            Root<Post> root4 = cq4.from(Post.class);
////            root4.fetch("postComments", JoinType.LEFT);
////            cq4.select(root4);
////            Query<Post> query4 = session.createQuery(cq4);
////            List<Post> postsWithComments = query4.getResultList();
////            postsWithComments.forEach(System.out::println);
////
////            // Запрос 5: Извлечение постов с автором
////            CriteriaQuery<Post> cq5 = cb.createQuery(Post.class);
////            Root<Post> root5 = cq5.from(Post.class);
////            root5.fetch("author", JoinType.INNER);
////            cq5.select(root5);
////            Query<Post> query5 = session.createQuery(cq5);
////            List<Post> postsWithAuthor = query5.getResultList();
////            postsWithAuthor.forEach(System.out::println);
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////    }
//}