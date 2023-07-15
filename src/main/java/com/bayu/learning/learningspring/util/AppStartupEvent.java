package com.bayu.learning.learningspring.util;

import com.bayu.learning.learningspring.business.ReservationService;
import com.bayu.learning.learningspring.business.RoomReservation;
import com.bayu.learning.learningspring.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class AppStartupEvent implements ApplicationListener<ApplicationReadyEvent> {
    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;
    private final ReservationRepository reservationRepository;
    private final ReservationService reservationService;
    private final DateUtils dateUtils;

    public AppStartupEvent(RoomRepository roomRepository, GuestRepository guestRepository, ReservationRepository reservationRepository, ReservationService reservationService, DateUtils dateUtils) {
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
        this.reservationService = reservationService;
        this.dateUtils = dateUtils;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Iterable<Room> rooms = this.roomRepository.findAll();
        Iterable<Guest> guests = this.guestRepository.findAll();
        Iterable<Reservation> reservations = this.reservationRepository.findAll();
        rooms.forEach(System.out::println);
        guests.forEach(System.out::println);
        reservations.forEach(System.out::println);

        Date date = this.dateUtils.createDateFromDateString("2022-01-01");
        List<RoomReservation> filteredReservations = this.reservationService.getRoomReservationForDate(date);
        filteredReservations.forEach(System.out::println);
    }
}
