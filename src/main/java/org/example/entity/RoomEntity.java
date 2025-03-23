package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.example.dto.Room;

@Entity
@Data
public class RoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String type;

    private Long price;

    private Boolean available;

    public Room getRoom(){
        Room room = new Room();
        room.setId(id);
        room.setName(name);
        room.setType(type);
        room.setAvailable(available);
        room.setPrice(price);
        return room;
    }
}
