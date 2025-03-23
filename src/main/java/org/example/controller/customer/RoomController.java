package org.example.controller.customer;

import lombok.RequiredArgsConstructor;
import org.example.service.customer.room.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
public class RoomController {

    private final RoomService roomService;

    @GetMapping("/rooms/{pageNumber}")
    public ResponseEntity<?> getAvailableRooms(@PathVariable int pageNumber) {
        System.out.println(pageNumber);
        return ResponseEntity.ok(roomService.getAvailableRooms(pageNumber));
    }
}