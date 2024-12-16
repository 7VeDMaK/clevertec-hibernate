package org.example.service;

import org.example.entity.CarShowroom;
import org.example.repository.CarShowroomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Service for CarShowroom Entity
@Service
public class CarShowroomService {

    private final CarShowroomRepository carShowroomRepository;

    public CarShowroomService(CarShowroomRepository carShowroomRepository) {
        this.carShowroomRepository = carShowroomRepository;
    }

    public CarShowroom addCarShowroom(CarShowroom carShowroom) {
        return carShowroomRepository.save(carShowroom);
    }

    public CarShowroom updateCarShowroom(Long id, CarShowroom updatedShowroom) {
        Optional<CarShowroom> existingShowroom = carShowroomRepository.findById(id);
        if (existingShowroom.isPresent()) {
            CarShowroom showroom = existingShowroom.get();
            showroom.setName(updatedShowroom.getName());
            showroom.setAddress(updatedShowroom.getAddress());
            return carShowroomRepository.save(showroom);
        } else {
            throw new RuntimeException("CarShowroom with id " + id + " not found.");
        }
    }

    public void deleteCarShowroom(Long id) {
        if (carShowroomRepository.existsById(id)) {
            carShowroomRepository.deleteById(id);
        } else {
            throw new RuntimeException("CarShowroom with id " + id + " not found.");
        }
    }

    public List<CarShowroom> getAllCarShowrooms() {
        return carShowroomRepository.findAll();
    }

    public Optional<CarShowroom> getCarShowroomById(Long id) {
        return carShowroomRepository.findById(id);
    }
}
