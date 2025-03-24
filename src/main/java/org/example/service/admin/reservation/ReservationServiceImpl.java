package org.example.service.admin.reservation;


import lombok.RequiredArgsConstructor;
import org.example.dto.ReservationResponse;
import org.example.entity.ReservationEntity;
import org.example.entity.RoomEntity;
import org.example.enums.ReservationStatus;
import org.example.repository.ReservationDao;
import org.example.repository.RoomDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationDao reservationDao;

    private final RoomDao roomDao;

    public static final int SEARCH_RESULT_PER_PAGE = 4;

    public ReservationResponse getAllReservations(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, SEARCH_RESULT_PER_PAGE);
        Page<ReservationEntity> reservationPage = reservationDao.findAll(pageable);

        ReservationResponse reservationResponseDto = new ReservationResponse();
        reservationResponseDto.setReservationList(reservationPage.stream().map(ReservationEntity::getReservationDto)
                .collect(Collectors.toList()));
        reservationResponseDto.setPageNumber(reservationPage.getPageable().getPageNumber());
        reservationResponseDto.setTotalPages(reservationPage.getTotalPages());

        return reservationResponseDto;
    }

    public boolean changeReservationStatus(Long reservationId, String status) {
        Optional<ReservationEntity> optionalReservation = reservationDao.findById(reservationId);
        if (optionalReservation.isPresent()) {
            ReservationEntity existingReservation = optionalReservation.get();
            if (Objects.equals(status, "APPROVED")) {
                existingReservation.setStatus(ReservationStatus.APPROVED);
            }else {
                existingReservation.setStatus(ReservationStatus.REJECTED);
            }

            reservationDao.save(existingReservation);

            RoomEntity existingRoom = existingReservation.getRoom();
            existingRoom.setAvailable(false);

            roomDao.save(existingRoom);

            return true;
        }
        return false;
    }
}
