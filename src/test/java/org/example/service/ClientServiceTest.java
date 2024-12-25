package org.example.service;

import org.example.entity.Car;
import org.example.entity.Client;
import org.example.repository.CarRepository;
import org.example.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private ClientService clientService;

    private Client client;
    private Car car;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Инициализация тестовых данных
        client = new Client();
        client.setId(1L);
        client.setName("John Doe");

        car = new Car();
        car.setId(1L);
        car.setModel("Tesla Model S");
        car.setBrand("Tesla");
        car.setYear(2022);
        car.setPrice(79999.99);
    }

    @Test
    void testBuyCar_Success() {
        // Given
        when(clientRepository.findById(client.getId())).thenReturn(Optional.of(client));
        when(carRepository.findById(car.getId())).thenReturn(Optional.of(car));
        when(clientRepository.save(any(Client.class))).thenReturn(client);

        // When
        clientService.buyCar(client, car);

        // Then
        assertTrue(client.getCars().contains(car), "Client should now own the car");

        verify(clientRepository, times(1)).save(client);
        verify(carRepository, never()).save(any(Car.class));
    }


    @Test
    void testBuyCar_AlreadyOwnedCar() {
        // Given
        client.getCars().add(car);
        when(clientRepository.findById(client.getId())).thenReturn(Optional.of(client));
        when(carRepository.findById(car.getId())).thenReturn(Optional.of(car));

        // When & Then
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            clientService.buyCar(client, car);
        });

        assertEquals("Client already owns this car.", exception.getMessage());
        verify(clientRepository, never()).save(client);
        verify(carRepository, never()).save(car);
    }

//    @Test
//    void testBuyCar_ClientNotFound() {
//        // Given
//        when(clientRepository.findById(client.getId())).thenReturn(Optional.empty());
//
//        // When & Then
//        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
//            clientService.buyCar(client, car);
//        });
//
//        assertEquals("Client not found", exception.getMessage());
//        verify(clientRepository, never()).save(client);
//        verify(carRepository, never()).save(car);
//    }
//
//    @Test
//    void testBuyCar_CarNotFound() {
//        // Given
//        when(clientRepository.findById(client.getId())).thenReturn(Optional.of(client));
//        when(carRepository.findById(car.getId())).thenReturn(Optional.empty());
//
//        // When & Then
//        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
//            clientService.buyCar(client, car);
//        });
//
//        assertEquals("Car not found", exception.getMessage());
//        verify(clientRepository, never()).save(client);
//        verify(carRepository, never()).save(car);
//    }

//    @Test
//    void testAddContactToClient() {
//        // Given
//        String contact = "client@example.com";
//        when(clientRepository.findById(client.getId())).thenReturn(Optional.of(client));
//
//        // When
//        clientService.addClient(client.getId(), contact);
//
//        // Then
//        assertTrue(client.getContacts().contains(contact));
//        verify(clientRepository, times(1)).save(client);
//    }

    @Test
    void testAddClient() {
        // Given
        when(clientRepository.save(any(Client.class))).thenReturn(client);

        // When
        Client registeredClient = clientService.addClient(client);

        // Then
        assertNotNull(registeredClient);
        verify(clientRepository, times(1)).save(client);
    }
}
