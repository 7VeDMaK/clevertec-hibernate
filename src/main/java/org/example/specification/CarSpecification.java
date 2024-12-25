package org.example.specification;

import org.example.entity.Car;
import org.springframework.data.jpa.domain.Specification;

public class CarSpecification {

    public static Specification<Car> hasBrand(String brand) {
        return (root, query, builder) ->
                (brand == null || brand.isBlank()) ?
                        builder.conjunction() : builder.equal(root.get("brand"), brand);
    }

    public static Specification<Car> hasYear(Integer year) {
        return (root, query, builder) ->
                (year == null || year == 0) ?
                        builder.conjunction() : builder.equal(root.get("year"), year);
    }

    public static Specification<Car> hasCategory(String category) {
        return (root, query, builder) ->
                (category == null || category.isBlank()) ?
                        builder.conjunction() : builder.equal(root.get("category").get("name"), category);
    }

    public static Specification<Car> priceBetween(Double minPrice, Double maxPrice) {
        return (root, query, builder) -> {
            if (minPrice == null && maxPrice == null) {
                return builder.conjunction();
            }
            if (minPrice != null && maxPrice != null) {
                return builder.between(root.get("price"), minPrice, maxPrice);
            }
            if (minPrice != null) {
                return builder.greaterThanOrEqualTo(root.get("price"), minPrice);
            }
            return builder.lessThanOrEqualTo(root.get("price"), maxPrice);
        };
    }
}
