package com.restorant.paskaita_2024_07_01_reservation.RestControllers;

import com.restorant.paskaita_2024_07_01_reservation.DataClasses.Client;
import com.restorant.paskaita_2024_07_01_reservation.Services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClientController {

    private final ClientService clientService;
    @Autowired
    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }


    @GetMapping("/clients")
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }


    @GetMapping("/clients/{id}")
    public Client getClientByID(@PathVariable int id){
        return clientService.getClientByID(id);
    }

    @PostMapping("/clients")
    public String createClients(@RequestBody Client client) {
        return clientService.createClient(client);
    }

     @PutMapping("/clients/{id}")
    public String editClientInformation(@PathVariable int id,@RequestBody Client client){
        return clientService.editClientInformation(id,client);
    }

    @GetMapping("/clients/search")
    public List<Client> getAllClientsByName(@RequestParam String name) {
        return clientService.getAllClientsByName(name);
    }




}
