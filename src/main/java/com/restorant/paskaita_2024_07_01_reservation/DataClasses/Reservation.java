package com.restorant.paskaita_2024_07_01_reservation.DataClasses;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Reservation {

    private int reservationID;
    private int clientID;
    @JsonProperty("reservationDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime reservationDate;
    private int numberOfPeople;
    private String status;

    public Reservation(){

    }

    public int getReservationID() {
        return reservationID;
    }
    public int getClientID() {
        return clientID;
    }
    public LocalDateTime getReservationDate() {
        return reservationDate;

    }
    public int getNumberOfPeople() {
        return numberOfPeople;
    }
    public String getStatus() {
        return status;
    }

    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public void setReservationDate(LocalDateTime reservationDate) {
        this.reservationDate = reservationDate;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return "ID: " + reservationID + " Client ID: " + clientID  + " reservation date: " + reservationDate.format(dateTimeFormatter) + " number of people: " + numberOfPeople + " Status: " + status + " \n";
    }

}
