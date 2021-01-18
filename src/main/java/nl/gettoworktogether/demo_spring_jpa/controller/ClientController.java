package nl.gettoworktogether.demo_spring_jpa.controller;

import nl.gettoworktogether.demo_spring_jpa.model.Client;
import nl.gettoworktogether.demo_spring_jpa.repository.ClientRepository;
import nl.gettoworktogether.demo_spring_jpa.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping(value = "/clients")
    public ResponseEntity<Object> getClients() {
        List<Client> clients = clientService.getAllClients();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @GetMapping(value = "/clients/{id}")
    public ResponseEntity<Object> getClient(@PathVariable("id") long id) {
        Client client = clientService.getClientById(id);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @DeleteMapping(value = "/clients/{id}")
    public ResponseEntity<Object> deleteClient(@PathVariable("id") long id) {
        clientService.deleteClient(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "/clients")
    public ResponseEntity<Object> saveClient(@RequestBody Client client) {
        long newId = clientService.saveClient(client);
        return new ResponseEntity<>(newId, HttpStatus.CREATED);
    }

    @PutMapping(value = "/clients/{id}")
    public ResponseEntity<Object> updateClient(@PathVariable("id") int id, @RequestBody Client client) {
        clientService.updateClient(id, client);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping(value = "/clients/{id}")
    public ResponseEntity<Object> partialUpdateClient(@PathVariable("id") int id, @RequestBody Client data) {
        clientService.updateClient(id, data);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/clients/lastname/{lastname}")
    public ResponseEntity<Object> getClientByLastName(@PathVariable("lastname") String lastName) {
        Client client = clientService.getClientByLastName(lastName);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

}
