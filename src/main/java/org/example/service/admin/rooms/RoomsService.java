package org.example.service.admin.rooms;

import org.example.dto.Room;
import org.example.dto.RoomsResponse;

public interface RoomsService {

    boolean postRoom(Room roomDto);

    RoomsResponse getAllRooms(int pageNumber);

    Room getRoomById(Long id);

    boolean updateRoom(Long id, Room roomDto);

    void deleteRoom(Long id);
}
