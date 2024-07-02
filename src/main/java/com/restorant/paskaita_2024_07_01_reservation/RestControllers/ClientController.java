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

    @PostMapping("/clients")
    public String createClients(@RequestBody Client client) {
        return clientService.createClient(client);
    }
    @GetMapping("/clients")
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }


}
