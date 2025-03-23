package org.example.service.admin.rooms;

import org.example.dto.Room;
import org.example.dto.RoomsResponse;

public interface RoomService {

    boolean postRoom(Room roomDto);

    RoomsResponse getAllRooms(int pageNumber);

    Room getRoomById(Long id);
}
