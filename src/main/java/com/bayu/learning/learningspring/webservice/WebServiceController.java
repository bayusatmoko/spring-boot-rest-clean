package com.bayu.learning.learningspring.webservice;

import com.bayu.learning.learningspring.business.ReservationService;
import com.bayu.learning.learningspring.business.RoomReservation;
import com.bayu.learning.learningspring.data.Guest;
import com.bayu.learning.learningspring.data.Room;
import com.bayu.learning.learningspring.util.DateUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class WebServiceController {
    private final DateUtils dateUtils;
    private final ReservationService reservationService;

    public WebServiceController(DateUtils dateUtils, ReservationService reservationService) {
        this.dateUtils = dateUtils;
        this.reservationService = reservationService;
    }

    @RequestMapping(path = "/reservations", method = RequestMethod.GET)
    public List<RoomReservation> getReservations(@RequestParam(value = "date", required = false) String dateString) {
        Date date = this.dateUtils.createDateFromDateString(dateString);
        return this.reservationService.getRoomReservationForDate(date);
    }

    @RequestMapping(path = "/guests", method = RequestMethod.GET)
    public List<Guest> getGuests() {
        return this.reservationService.getHotelGuests();
    }

    @PostMapping(path = "/guests")
    @ResponseStatus(HttpStatus.CREATED)
    public void addGuest(@RequestBody Guest guest) {
        this.reservationService.addGuest(guest);
    }

    @RequestMapping(path = "/rooms")
    public List<Room> getRooms() {
        return this.reservationService.getRooms();
    }
}
