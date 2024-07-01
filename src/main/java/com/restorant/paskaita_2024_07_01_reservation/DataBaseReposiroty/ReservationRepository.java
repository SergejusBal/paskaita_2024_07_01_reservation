package com.restorant.paskaita_2024_07_01_reservation.DataBaseReposiroty;

import com.restorant.paskaita_2024_07_01_reservation.DataClasses.Client;
import com.restorant.paskaita_2024_07_01_reservation.DataClasses.Reservation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;


import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Repository
public class ReservationRepository {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    public String createReservation(Reservation reservation) {
        String sql = "INSERT INTO reservation_database.reservations (client_id,reservation_date,number_of_people,status)\n" +
                "VALUES (?,?,?,?);";
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,reservation.getClientID());
            preparedStatement.setString(2,reservation.getReservationDate().toString());
            preparedStatement.setInt(3,reservation.getNumberOfPeople());
            preparedStatement.setString(4,"Not confirmed");

            preparedStatement.executeUpdate();

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "Reservation was successfully added";
    }
    public String getAllReservation() {
        String sql = "SELECT * FROM reservation_database.reservations;";
        StringBuilder stringbuilder = new StringBuilder();

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet =  preparedStatement.executeQuery();

            Reservation reservation = new Reservation();
            while (resultSet.next()){

                reservation.setReservationID(resultSet.getInt("reservation_id"));
                reservation.setClientID(resultSet.getInt("client_id"));
                reservation.setNumberOfPeople(resultSet.getInt("number_of_people"));
                reservation.setStatus(resultSet.getString("status"));

                LocalDateTime localDateTime = formatDateTime(resultSet.getString("reservation_date"));
                reservation.setReservationDate(localDateTime);

                stringbuilder.append(reservation);
            }

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return stringbuilder.toString();
    }

    public String getAllReservationByClientID(Integer clientId) {
        String sql = "SELECT * FROM reservation_database.reservations WHERE client_id = ?;";
        StringBuilder stringbuilder = new StringBuilder();

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,clientId);
            ResultSet resultSet =  preparedStatement.executeQuery();

            Reservation reservation = new Reservation();
            while (resultSet.next()){

                reservation.setReservationID(resultSet.getInt("reservation_id"));
                reservation.setClientID(resultSet.getInt("client_id"));
                reservation.setNumberOfPeople(resultSet.getInt("number_of_people"));
                reservation.setStatus(resultSet.getString("status"));

                LocalDateTime localDateTime = formatDateTime(resultSet.getString("reservation_date"));
                reservation.setReservationDate(localDateTime);

                stringbuilder.append(reservation);
            }

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return stringbuilder.toString();

    }

    public String getAllReservationWithStatus(String status){
        String sql = "SELECT * FROM reservation_database.reservations WHERE status = ?;";
        StringBuilder stringbuilder = new StringBuilder();

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,status);
            ResultSet resultSet =  preparedStatement.executeQuery();

            Reservation reservation = new Reservation();
            while (resultSet.next()){

                reservation.setReservationID(resultSet.getInt("reservation_id"));
                reservation.setClientID(resultSet.getInt("client_id"));
                reservation.setNumberOfPeople(resultSet.getInt("number_of_people"));
                reservation.setStatus(resultSet.getString("status"));

                LocalDateTime localDateTime = formatDateTime(resultSet.getString("reservation_date"));
                reservation.setReservationDate(localDateTime);

                stringbuilder.append(reservation);
            }

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return stringbuilder.toString();

    }

    public String changeReservationStatus(Integer reservationId, String status){

        String sql = "UPDATE reservation_database.reservations SET status = ? WHERE reservation_id = ?";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1,status);
            preparedStatement.setInt(2,reservationId);
            preparedStatement.executeUpdate();

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "Reservation was " + status;

    }


    public String getReservationAtDate(String date){
        String sql = "SELECT * FROM reservation_database.reservations WHERE DATE(reservation_date) = ?;";
        StringBuilder stringbuilder = new StringBuilder();

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,date);
            ResultSet resultSet =  preparedStatement.executeQuery();

            Reservation reservation = new Reservation();
            while (resultSet.next()){

                reservation.setReservationID(resultSet.getInt("reservation_id"));
                reservation.setClientID(resultSet.getInt("client_id"));
                reservation.setNumberOfPeople(resultSet.getInt("number_of_people"));
                reservation.setStatus(resultSet.getString("status"));

                LocalDateTime localDateTime = formatDateTime(resultSet.getString("reservation_date"));
                reservation.setReservationDate(localDateTime);

                stringbuilder.append(reservation);
            }

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return stringbuilder.toString();

    }






    private LocalDateTime formatDateTime(String dateTime){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime rentalDate = null;
        try {
            rentalDate = LocalDateTime.parse(dateTime, dateTimeFormatter);
        }catch(DateTimeParseException | NullPointerException e) {
            rentalDate = LocalDateTime.parse("1900-01-01 00:00:00", dateTimeFormatter);
        }
        return rentalDate;
    }
    private LocalDate formatDate(String dateTime){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate year = null;
        try {
            year = LocalDate.parse(dateTime, dateTimeFormatter);
        }catch(DateTimeParseException | NullPointerException e) {
            year = LocalDate.parse("1900-01-01",dateTimeFormatter);
        }
        return year;
    }


}

