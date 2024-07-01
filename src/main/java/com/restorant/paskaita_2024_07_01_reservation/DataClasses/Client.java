package com.restorant.paskaita_2024_07_01_reservation.DataClasses;

public class Client {

    private int clientID;
    private String name;
    private String email;
    private String phone;

    public Client() {
    }

    public int getClientID() {
        return clientID;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "ID: " + clientID + " Name: " + name +  " e-mail: " + email + " phone number: " + phone + " \n";
    }
}
