package org.example.service.customer.booking;

import org.example.dto.Reservation;
import org.example.dto.ReservationResponse;

public interface BookingService {

    boolean postReservation(Reservation reservationDto);

    ReservationResponse getAllReservationByUserId(Long userId, int pageNumber);
}
