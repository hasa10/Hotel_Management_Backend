package org.example.controller.customer;

import lombok.RequiredArgsConstructor;
import org.example.service.customer.room.RoomsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
public class RoomsController {

    private final RoomsService roomsService;

    @GetMapping("rooms/page-number")
    public ResponseEntity<?> getAvailableRooms(@PathVariable int pageNumber){
        return ResponseEntity.ok(roomsService.getAvailableRooms(pageNumber));
    }
}
