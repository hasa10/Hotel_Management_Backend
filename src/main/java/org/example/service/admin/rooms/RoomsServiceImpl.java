package org.example.service.admin.rooms;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.dto.Room;
import org.example.dto.RoomsResponse;
import org.example.entity.RoomEntity;
import org.example.repository.RoomDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

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

    public RoomsResponse getAllRooms(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, 6);
        Page<RoomEntity> roomPage = roomDao.findAll(pageable);

        RoomsResponse roomsResponse = new RoomsResponse();
        roomsResponse.setPageNumber(roomPage.getPageable().getPageNumber());
        roomsResponse.setTotalPage(roomPage.getTotalPages());
        roomsResponse.setRoomDtoList(roomPage.stream().map(RoomEntity::getRoom).collect(Collectors.toList()));

        return roomsResponse;
    }

    public Room getRoomById(Long id) {
        Optional<RoomEntity> roomEntity = roomDao.findById(id);
        if (roomEntity.isPresent()) {
            return roomEntity.get().getRoom();
        }else {
            throw new EntityNotFoundException("Room not found");
        }
    }

    public boolean updateRoom(Long id, Room roomDto) {
        Optional<RoomEntity> optionalRoom = roomDao.findById(id);
        if (optionalRoom.isPresent()) {
            RoomEntity existingRoom = optionalRoom.get();

            existingRoom.setName(roomDto.getName());
            existingRoom.setPrice(roomDto.getPrice());
            existingRoom.setType(roomDto.getType());

            roomDao.save(existingRoom);
            return true;
        }
        return false;
    }

    public void deleteRoom(Long id) {
        Optional<RoomEntity> optionalRoom = roomDao.findById(id);
        if (optionalRoom.isPresent()) {
            roomDao.deleteById(id);
        }else {
            throw new EntityNotFoundException("Room not found");
        }
    }
}
