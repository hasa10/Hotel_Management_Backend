package org.example.service.customer.room;


import lombok.RequiredArgsConstructor;
import org.example.dto.RoomsResponse;
import org.example.entity.RoomEntity;
import org.example.repository.RoomDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomsServiceImpl {

    private final RoomDao roomDao;

    public RoomsResponse getAvailableRooms(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, 6);
        Page<RoomEntity> roomPage = roomDao.findByAvailable(true,pageable);

        RoomsResponse roomsResponse = new RoomsResponse();
        roomsResponse.setPageNumber(roomPage.getPageable().getPageNumber());
        roomsResponse.setTotalPage(roomPage.getTotalPages());
        roomsResponse.setRoomDtoList(roomPage.stream().map(RoomEntity::getRoom).collect(Collectors.toList()));

        return roomsResponse;
    }
}
