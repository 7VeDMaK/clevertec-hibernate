package org.example.service;

import org.example.entity.Client;
import org.example.exception.NotFoundClientException;
import org.example.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Service for Client Entity
@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client addClient(Client client) {
        return clientRepository.save(client);
    }

//    public Car buyCar(Client client, Car car) {
//        if (client.getCars().contains(car)) {
//            throw new IllegalStateException("This car is already purchased by another client.");
//        }
//
//        car.setClient(client);
//        client.getCars().add(car);
//
//        return carRepository.save(car);
//    }


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

