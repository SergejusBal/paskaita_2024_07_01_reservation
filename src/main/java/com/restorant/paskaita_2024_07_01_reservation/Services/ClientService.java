package com.restorant.paskaita_2024_07_01_reservation.Services;

import com.restorant.paskaita_2024_07_01_reservation.DataBaseReposiroty.ClientRepository;
import com.restorant.paskaita_2024_07_01_reservation.DataClasses.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    @Autowired
    public ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    public String createClient(Client client){

        if( client.getName() == null || client.getEmail() == null || client.getPhone() == null)  return "Invalid Input";

        return clientRepository.createClient(client);
    }

    public List<Client> getAllClients() {
        return clientRepository.getAllClients();
    }

    public Client getClientByID(int clientID){
        return clientRepository.getClientByID(clientID);
    }

    public String editClientInformation(int client_id,Client client){

        if( client.getName() == null || client.getEmail() == null || client.getPhone() == null || client_id == 0)  return "Invalid Input";

        return clientRepository.editClientInformation(client_id,client);
    }
}
