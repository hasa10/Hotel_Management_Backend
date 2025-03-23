package org.example.service.customer.room;


import org.example.dto.RoomsResponse;

public interface RoomsService {

    RoomsResponse getAvailableRooms(int pageNumber);
}
