package com.restorant.paskaita_2024_07_01_reservation.Services;

import com.restorant.paskaita_2024_07_01_reservation.DataBaseReposiroty.ReservationRepository;
import com.restorant.paskaita_2024_07_01_reservation.DataClasses.Client;
import com.restorant.paskaita_2024_07_01_reservation.DataClasses.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    @Autowired
    public ReservationService(ReservationRepository reservationRepository){
        this.reservationRepository = reservationRepository;
    }

    public String createReservation(Reservation reservation) {

        if( reservation.getClientID() == 0 || reservation.getReservationDate() == null || reservation.getNumberOfPeople() == 0)  return "Invalid Input";

        return reservationRepository.createReservation(reservation);
    }

    public List<Reservation> getAllReservation(String date) {
       if (date == null)  return reservationRepository.getAllReservation();
       else return reservationRepository.getReservationAtDate(date);
    }
    public List<Reservation> getAllReservationByClientID(int clientId) {
        return reservationRepository.getAllReservationByClientID(clientId);
    }
    public List<Reservation>  getAllConfirmedReservation(){
        return reservationRepository.getAllReservationWithStatus("confirmed");
    }

    public List<Reservation>  getAllCanceledReservation(){
        return reservationRepository.getAllReservationWithStatus("canceled");
    }

    public String confirmReservation(int reservationId){
        return reservationRepository.changeReservationStatus(reservationId, "confirmed");
    }

    public String cancelReservation(int reservationId){
        return reservationRepository.changeReservationStatus(reservationId,"canceled");
    }

    public String editReservationInformation(int reservationID,Reservation reservation){
        if( reservation.getClientID() == 0 || reservation.getReservationDate() == null || reservation.getNumberOfPeople() == 0 || reservation.getStatus() == null || reservationID == 0)  return "Invalid Input";
        return reservationRepository.editReservationInformation(reservationID,reservation);
    }

    public Reservation getReservationByID(int reservationID){
        return reservationRepository.getReservationByID(reservationID);
    }

    public long getReservationCount() {
        return reservationRepository.getReservationCount();
    }


}
