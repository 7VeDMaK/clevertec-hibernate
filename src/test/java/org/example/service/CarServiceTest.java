package org.example.service;

import org.example.entity.Car;
import org.example.entity.CarShowroom;
import org.example.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentCaptor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CarServiceTest {

    private CarRepository carRepository;
    private CarService carService;

    @BeforeEach
    void setUp() {
        carRepository = mock(CarRepository.class);
        carService = new CarService(carRepository);
    }

    static Stream<Car> carProvider() {
        return Stream.of(
                CarTestData.createTestCar1(),
                CarTestData.createTestCar2()
        );
    }

    @ParameterizedTest
    @MethodSource("carProvider")
    void testGetCarByIdWithMultipleCars(Car testCar) {
        when(carRepository.findById(testCar.getId())).thenReturn(Optional.of(testCar));

        Optional<Car> result = carService.getCarById(testCar.getId());

        assertEquals(testCar, result.orElse(null));

        verify(carRepository, times(1)).findById(testCar.getId());

        reset(carRepository);
    }

    @Test
    void testAssignCarToShowroomById() {
        Car testCar = CarTestData.createTestCar1();
        CarShowroom showroom = new CarShowroom();
        showroom.setId(3L);
        showroom.setName("Luxury Autos");

        when(carRepository.findById(testCar.getId())).thenReturn(Optional.of(testCar));
        when(carRepository.save(testCar)).thenReturn(testCar);

        Car result = carService.assignCarToShowroom(testCar.getId(), showroom);

        assertEquals(showroom, result.getCarShowroom());
        verify(carRepository, times(1)).save(testCar);
        verify(carRepository, times(1)).findById(testCar.getId());
    }

    @Test
    void testAssignCarToShowroom() {
        Car testCar = CarTestData.createTestCar1();
        CarShowroom showroom = new CarShowroom();
        showroom.setId(4L);
        showroom.setName("Super Cars");

        when(carRepository.save(testCar)).thenAnswer(invocation -> {
            Car savedCar = invocation.getArgument(0);
            savedCar.setCarShowroom(showroom);
            return savedCar;
        });

        Car result = carService.assignCarToShowroom(testCar, showroom);

        assertNotNull(result);
        assertEquals(showroom, result.getCarShowroom());
        verify(carRepository, times(1)).save(testCar);
    }

    @Test
    void testFindCarsByFilters() {
        // Given
        Car car1 = CarTestData.createTestCar1();
        Car car2 = CarTestData.createTestCar2();

        when(carRepository.findCarsByFilters(
                anyString(), anyString(), anyInt(), anyDouble(), anyDouble()
        )).thenReturn(List.of(car1, car2));

        // When
        List<Car> result = carService.findCarsByFilters("Tesla", "Electric", 2022, 50000, 100000);

        // Then
        assertEquals(2, result.size());
        verify(carRepository, times(1)).findCarsByFilters(
                anyString(), anyString(), anyInt(), anyDouble(), anyDouble()
        );

        ArgumentCaptor<String> brandCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> categoryCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Integer> yearCaptor = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<Double> minPriceCaptor = ArgumentCaptor.forClass(Double.class);
        ArgumentCaptor<Double> maxPriceCaptor = ArgumentCaptor.forClass(Double.class);

        verify(carRepository).findCarsByFilters(
                brandCaptor.capture(),
                categoryCaptor.capture(),
                yearCaptor.capture(),
                minPriceCaptor.capture(),
                maxPriceCaptor.capture()
        );

        assertEquals("Tesla", brandCaptor.getValue());
        assertEquals("Electric", categoryCaptor.getValue());
        assertEquals(2022, yearCaptor.getValue());
        assertEquals(50000, minPriceCaptor.getValue());
        assertEquals(100000, maxPriceCaptor.getValue());
    }


}
