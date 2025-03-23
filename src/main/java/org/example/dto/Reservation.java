package org.example.dto;

import lombok.Data;
import org.example.enums.ReservationStatus;

import java.time.LocalDate;

@Data
public class Reservation {

    private Long id;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private Long price;

    private ReservationStatus status;

    private Long roomId;

    private String roomType;

    private String roomName;

    private Long userId;

    private String userName;
}
