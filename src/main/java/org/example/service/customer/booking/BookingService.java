package org.example.service.customer.booking;

import org.example.dto.Reservation;

public interface BookingService {

    boolean postReservation(Reservation reservationDto);
}
