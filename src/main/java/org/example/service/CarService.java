package org.example.service;

import org.example.entity.Car;
import org.example.entity.CarShowroom;
import org.example.exception.NotFoundCarException;
import org.example.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car addCar(Car car) {
        return carRepository.save(car);
    }

    public Car addCarToShowroom(Car car, CarShowroom carShowroom) {
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

