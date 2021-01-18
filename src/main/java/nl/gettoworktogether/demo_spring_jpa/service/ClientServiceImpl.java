package nl.gettoworktogether.demo_spring_jpa.service;

import nl.gettoworktogether.demo_spring_jpa.exception.DatabaseErrorException;
import nl.gettoworktogether.demo_spring_jpa.exception.RecordNotFoundException;
import nl.gettoworktogether.demo_spring_jpa.model.Client;
import nl.gettoworktogether.demo_spring_jpa.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client getClientById(long id) {
        if (clientRepository.existsById(id)) {
            return clientRepository.findById(id).orElse(null);
        }
        else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public void deleteClient(long id) {
        if (clientRepository.existsById(id)) {
            clientRepository.deleteById(id);
        }
        else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public long saveClient(Client client) {
        Client newClient = clientRepository.save(client);
        return newClient.getId();
    }

    @Override
    public void updateClient(long id, Client client) {
        if (clientRepository.existsById(id)) {
            try {
                Client existingClient = clientRepository.findById(id).orElse(null);
                existingClient.setFirstName(client.getFirstName());
                existingClient.setLastName(client.getLastName());
                existingClient.setClientNr(client.getClientNr());
                clientRepository.save(existingClient);
            }
            catch (Exception ex) {
                throw new DatabaseErrorException();
            }
        }
        else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public Client getClientByLastName(String lastName) {
        try {
            return clientRepository.findByLastNameIgnoreCase(lastName);
        }
        catch (Exception ex ) {
            throw new RecordNotFoundException();
        }
    }

    public Client getClientByLastName2(String lastName) {
        Client client = clientRepository.findByLastNameIgnoreCase(lastName);
        if (client == null) {
            throw new RecordNotFoundException();
        }
        else {
            return client;
        }
    }

}
