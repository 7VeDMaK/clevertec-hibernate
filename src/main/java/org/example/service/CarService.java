package org.example.service;

import org.example.entity.Car;
import org.example.entity.CarShowroom;
import org.example.entity.Category;
import org.example.exception.NotFoundCarException;
import org.example.repository.CarRepository;
import org.example.specification.CarSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car createCar(String model, String brand, int year,
                         double price, Category category,
                         CarShowroom carShowroom) {
        Car car = new Car();
        car.setModel(model);
        car.setBrand(brand);
        car.setYear(year);
        car.setPrice(price);
        car.setCategory(category);
        car.setCarShowroom(carShowroom);
        return carRepository.save(car);
    }


    public Car addCar(Car car) {
        return carRepository.save(car);
    }

    public Car assignCarToShowroom(Car car, CarShowroom carShowroom) {
        car.setCarShowroom(carShowroom);
        return carRepository.save(car);
    }

    public Car assignCarToShowroom(Long carId, CarShowroom carShowroom) {
        Optional<Car> existingCar = carRepository.findById(carId);

        if (existingCar.isPresent()) {
            Car car = existingCar.get();
            car.setCarShowroom(carShowroom);
            return carRepository.save(car);
        } else {
            throw new NotFoundCarException(carId);
        }
    }

    public List<Car> findCarsByFilters(String brand, String category,
                                       int year, double minPrice,
                                       double maxPrice){
        return carRepository.findCarsByFilters(brand, category, year, minPrice, maxPrice);
    }

    public Page<Car> searchCars(String brand, Integer year, String category,
                                Double minPrice, Double maxPrice, Pageable pageable) {
        Specification<Car> spec = Specification
                .where(CarSpecification.hasBrand(brand))
                .and(CarSpecification.hasYear(year))
                .and(CarSpecification.hasCategory(category))
                .and(CarSpecification.priceBetween(minPrice, maxPrice));

        return carRepository.findAll(spec, pageable);
    }

    public Car updateCar(Long id, Car updatedCar) {
        Optional<Car> existingCar = carRepository.findById(id);
        if (existingCar.isPresent()) {
            Car car = existingCar.get();
            car.setModel(updatedCar.getModel());
            car.setBrand(updatedCar.getBrand());
            car.setYear(updatedCar.getYear());
            car.setPrice(updatedCar.getPrice());
            car.setCategory(updatedCar.getCategory());
            car.setCarShowroom(updatedCar.getCarShowroom());
            return carRepository.save(car);
        } else {
            throw new NotFoundCarException(id);
        }
    }

    public void deleteCar(Long id) {
        if (carRepository.existsById(id)) {
            carRepository.deleteById(id);
        } else {
            throw new NotFoundCarException(id);
        }
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Optional<Car> getCarById(Long id) {
        return carRepository.findById(id);
    }
}

