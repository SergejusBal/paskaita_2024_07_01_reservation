package com.restorant.paskaita_2024_07_01_reservation.RestControllers;

import com.restorant.paskaita_2024_07_01_reservation.DataClasses.Client;
import com.restorant.paskaita_2024_07_01_reservation.DataClasses.Reservation;
import com.restorant.paskaita_2024_07_01_reservation.Services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
public class ReservationController {

    private final ReservationService reservationService;
    @Autowired
    public ReservationController(ReservationService reservationService){
        this.reservationService = reservationService;
    }


    @PostMapping("/reservations")
    public String createReservation(@RequestBody Reservation reservation) {
        return reservationService.createReservation(reservation);
    }
    @GetMapping("/reservations")
    public String getAllReservation(@RequestParam(required = false) String date) {
        return reservationService.getAllReservation(date);
    }

    @GetMapping("/reservations/client/{clientId}")
    public String getAllReservationByClientID(@PathVariable Integer clientId){
        return reservationService.getAllReservationByClientID(clientId);
    }

    @GetMapping("/reservations/confirmed")
    public String getAllConfirmedReservation(){
        return reservationService.getAllConfirmedReservation();
    }

    @GetMapping("/reservations/canceled")
    public String getAllCanceledReservation(){
        return reservationService.getAllCanceledReservation();
    }

    @PatchMapping("/reservations/confirm/{reservationId}")
    public String confirmReservation(@PathVariable Integer reservationId) {
        return reservationService.confirmReservation(reservationId);
    }


    @DeleteMapping("/reservations/{reservationId}")
    public String cancelReservation(@PathVariable Integer reservationId){
        return reservationService.cancelReservation(reservationId);
    }









}
