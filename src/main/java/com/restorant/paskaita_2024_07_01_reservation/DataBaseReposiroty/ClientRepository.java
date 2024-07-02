package com.restorant.paskaita_2024_07_01_reservation.DataBaseReposiroty;

import com.restorant.paskaita_2024_07_01_reservation.DataClasses.Client;
import com.restorant.paskaita_2024_07_01_reservation.DataClasses.Reservation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ClientRepository {
    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    public String createClient(Client client){
        String sql = "INSERT INTO reservation_database.clients (name,email,phone)\n" +
                "VALUES (?,?,?);";
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,client.getName());
            preparedStatement.setString(2,client.getEmail());
            preparedStatement.setString(3,client.getPhone());

            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return "Client was successfully added ";

    }

    public List<Client> getAllClients() {
        String sql = "SELECT * FROM reservation_database.clients;";
        List<Client> clientList = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet =  preparedStatement.executeQuery();


            while (resultSet.next()){
                Client client = new Client();
                client.setClientID(resultSet.getInt("client_id"));
                client.setName(resultSet.getString("name"));
                client.setEmail(resultSet.getString("email"));
                client.setPhone(resultSet.getString("phone"));

                clientList.add(client);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return clientList;
    }

    public String editClientInformation(int client_id,Client client){
        String sql = "UPDATE reservation_database.clients SET name = ?, email = ?, phone = ? WHERE client_id = ?";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1,client.getName());
            preparedStatement.setString(2,client.getEmail());
            preparedStatement.setString(3,client.getPhone());
            preparedStatement.setInt(4,client_id);
            preparedStatement.executeUpdate();


            preparedStatement.close();
            connection.close();

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return "Client was edited successfully";
    }

    public Client getClientByID(int clientID){
        String sql = "SELECT * FROM reservation_database.clients WHERE client_id = ?;";
        Client client;

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,clientID);
            ResultSet resultSet =  preparedStatement.executeQuery();

            client = new Client();
            resultSet.next();

            client.setClientID(resultSet.getInt("client_id"));
            client.setName(resultSet.getString("name"));
            client.setEmail(resultSet.getString("email"));
            client.setPhone(resultSet.getString("phone"));

            resultSet.close();
            preparedStatement.close();
            connection.close();

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return client;

    }

    public List<Client> getAllClientsByName(String name){

        String sql = "SELECT * FROM reservation_database.clients WHERE name = ?;";
        List<Client> clientList = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,name);
            ResultSet resultSet =  preparedStatement.executeQuery();


            while (resultSet.next()){
                Client client = new Client();
                client.setClientID(resultSet.getInt("client_id"));
                client.setName(resultSet.getString("name"));
                client.setEmail(resultSet.getString("email"));
                client.setPhone(resultSet.getString("phone"));

                clientList.add(client);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return clientList;

    }

}
