package org.example.service;

import jakarta.transaction.Transactional;
import org.example.entity.Car;
import org.example.entity.Client;
import org.example.exception.NotFoundClientException;
import org.example.repository.CarRepository;
import org.example.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Service for Client Entity
@Service
public class ClientService {

    private final ClientRepository clientRepository;

    private final CarRepository carRepository;

    public ClientService(ClientRepository clientRepository, CarRepository carRepository) {
        this.clientRepository = clientRepository;
        this.carRepository = carRepository;
    }

    public Client addClient(Client client) {
        return clientRepository.save(client);
    }

    @Transactional
    public void buyCar(Client client, Car car) {
        if (client == null || car == null) {
            throw new IllegalArgumentException("Client and Car must not be null");
        }

        if (client.getCars().contains(car)) {
            throw new IllegalStateException("Client already owns this car.");
        }

        client.getCars().add(car);
        clientRepository.save(client);
    } //Add more exception info


    public Client updateClient(Long id, Client updatedClient) {
        Optional<Client> existingClient = clientRepository.findById(id);
        if (existingClient.isPresent()) {
            Client client = existingClient.get();
            client.setName(updatedClient.getName());
            client.setContacts(updatedClient.getContacts());
            client.setRegistrationDate(updatedClient.getRegistrationDate());
            return clientRepository.save(client);
        } else {
            throw new NotFoundClientException(id);
        }
    }

    public void deleteClient(Long id) {
        if (clientRepository.existsById(id)) {
            clientRepository.deleteById(id);
        } else {
            throw new NotFoundClientException(id);
        }
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Optional<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }
}

