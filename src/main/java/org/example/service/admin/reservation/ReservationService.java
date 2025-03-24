package org.example.service.admin.reservation;

import org.example.dto.ReservationResponse;

public interface ReservationService {

    ReservationResponse getAllReservations(int pageNumber);

    boolean changeReservationStatus(Long reservationId, String status);
}
