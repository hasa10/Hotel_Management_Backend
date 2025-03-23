package org.example.service.customer.room;


import org.example.dto.RoomsResponse;

public interface RoomService {

    RoomsResponse getAvailableRooms(int pageNumber);
}
