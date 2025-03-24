package org.example.dto;

import lombok.Data;

import java.util.List;

@Data
public class ReservationResponse {

    private Integer totalPages;

    private Integer pageNumber;

    private List<Reservation> reservationList;
}
