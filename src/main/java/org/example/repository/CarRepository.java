package org.example.repository;

import org.example.entity.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    @EntityGraph(attributePaths = {"category", "carShowroom"})
    @Query("SELECT c FROM Car c " +
            "JOIN c.category cat " +
            "WHERE (:brand IS NULL OR c.brand = :brand) " +
            "AND (:category IS NULL OR c.category = :category) " +
            "AND (:year = 0 OR c.year = :year) " +
            "AND (:minPrice = 0 OR c.price >= :minPrice) " +
            "AND (:maxPrice = 0 OR c.price <= :maxPrice)")
    List<Car> findCarsByFilters(
            @Param("brand") String brand,
            @Param("category") String category,
            @Param("year") int year,
            @Param("minPrice") double minPrice,
            @Param("maxPrice") double maxPrice
    );

    @EntityGraph(attributePaths = {"category", "carShowroom"})
    List<Car> findAll(Specification<Car> spec);

    @EntityGraph(attributePaths = {"category", "carShowroom"})
    Page<Car> findAll(Specification<Car> spec, Pageable pageable);
}
