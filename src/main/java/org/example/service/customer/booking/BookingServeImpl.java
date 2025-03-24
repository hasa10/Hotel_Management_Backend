package org.example.service.customer.booking;

import com.fasterxml.jackson.annotation.OptBoolean;
import lombok.RequiredArgsConstructor;
import org.example.dto.Reservation;
import org.example.dto.ReservationResponse;
import org.example.entity.ReservationEntity;
import org.example.entity.RoomEntity;
import org.example.entity.UserEntity;
import org.example.enums.ReservationStatus;
import org.example.repository.ReservationDao;
import org.example.repository.RoomDao;
import org.example.repository.UserDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.example.service.admin.reservation.ReservationServiceImpl.SEARCH_RESULT_PER_PAGE;

@Service
@RequiredArgsConstructor
public class BookingServeImpl implements BookingService {

    private final UserDao userDao;

    private final RoomDao roomDao;

    private final ReservationDao reservationDao;

    public static final int SEARCH_RESULT_PER_PAGE = 4;

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

    public ReservationResponse getAllReservationByUserId(Long userId, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, SEARCH_RESULT_PER_PAGE);
        Page<ReservationEntity> reservationPage = reservationDao.findAllByUserId(pageable, userId);

        ReservationResponse reservationResponseDto = new ReservationResponse();
        reservationResponseDto.setReservationList(reservationPage.stream().map(ReservationEntity::getReservationDto)
                .collect(Collectors.toList()));
        reservationResponseDto.setPageNumber(reservationPage.getPageable().getPageNumber());
        reservationResponseDto.setTotalPages(reservationPage.getTotalPages());

        return reservationResponseDto;
    }
}
