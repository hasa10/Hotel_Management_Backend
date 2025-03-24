package org.example.entity;


import jakarta.persistence.*;
import lombok.Data;
import org.example.dto.Reservation;
import org.example.enums.ReservationStatus;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Data
public class ReservationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private Long price;

    private ReservationStatus status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "room_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private RoomEntity room;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserEntity user;


    public Reservation getReservationDto() {
        Reservation reservation = new Reservation();

        reservation.setId(id);
        reservation.setPrice(price);
        reservation.setCheckInDate(checkInDate);
        reservation.setCheckOutDate(checkOutDate);
        reservation.setStatus(status);

        reservation.setUserId(user.getId());
        reservation.setUserName(user.getName());

        reservation.setRoomId(room.getId());
        reservation.setRoomName(room.getName());
        reservation.setRoomType(room.getType());

        return reservation;
    }
}
