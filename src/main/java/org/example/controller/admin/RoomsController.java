package org.example.controller.admin;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.dto.Room;
import org.example.service.admin.rooms.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class RoomsController {

    private final RoomService roomService;

    @PostMapping("/rooms")
    public ResponseEntity<?> responseEntity(@RequestBody Room room){
        boolean success = roomService.postRoom(room);
        if (success) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/rooms/{pageNumber}")
    public ResponseEntity<?> getAllRooms(@PathVariable int pageNumber){
        return ResponseEntity.ok(roomService.getAllRooms(pageNumber));
    }

    @GetMapping("/room/{id}")
    public ResponseEntity<?> getRoomById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(roomService.getRoomById(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }

    @PutMapping("/room/{id}")
    public ResponseEntity<?> updateRoom(@PathVariable Long id, @RequestBody Room room){
        boolean success = roomService.updateRoom(id, room);
        if (success) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
