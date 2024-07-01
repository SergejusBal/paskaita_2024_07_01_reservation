package com.restorant.paskaita_2024_07_01_reservation.DataBaseReposiroty;

import com.restorant.paskaita_2024_07_01_reservation.DataClasses.Client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
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

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return clientList;
    }

}
