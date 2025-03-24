package org.example.service.customer.booking;

import com.fasterxml.jackson.annotation.OptBoolean;
import lombok.RequiredArgsConstructor;
import org.example.dto.Reservation;
import org.example.entity.ReservationEntity;
import org.example.entity.RoomEntity;
import org.example.entity.UserEntity;
import org.example.enums.ReservationStatus;
import org.example.repository.ReservationDao;
import org.example.repository.RoomDao;
import org.example.repository.UserDao;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingServeImpl implements BookingService {

    private final UserDao userDao;

    private final RoomDao roomDao;

    private final ReservationDao reservationDao;

    public boolean postReservation(Reservation reservationDto) {
        Optional<UserEntity> optionalUser = userDao.findById(reservationDto.getUserId());
        Optional<RoomEntity> optionalRoom = roomDao.findById(reservationDto.getRoomId());

        if (optionalRoom.isPresent() && optionalUser.isPresent()) {
            ReservationEntity reservation = new ReservationEntity();
            reservation.setRoom(optionalRoom.get());
            reservation.setUser(optionalUser.get());
            reservation.setCheckInDate(reservationDto.getCheckInDate());
            reservation.setCheckOutDate(reservationDto.getCheckOutDate());
            reservation.setStatus(ReservationStatus.PENDING);

            Long days = ChronoUnit.DAYS.between(reservationDto.getCheckInDate(), reservationDto.getCheckOutDate());
            reservation.setPrice(optionalRoom.get().getPrice() * days);

            reservationDao.save(reservation);
            return true;
        }
        return false;
    }
}
