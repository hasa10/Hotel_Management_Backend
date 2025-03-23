package org.example.service.admin.rooms;

import lombok.RequiredArgsConstructor;
import org.example.dto.Room;
import org.example.entity.RoomEntity;
import org.example.repository.RoomDao;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomDao roomDao;

    public boolean postRoom(Room roomDto) {
        try{
            RoomEntity room = new RoomEntity();

            room.setName(roomDto.getName());
            room.setPrice(roomDto.getPrice());
            room.setType(roomDto.getType());
            room.setAvailable(true);

            roomDao.save(room);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
