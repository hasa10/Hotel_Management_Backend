package org.example.dto;

import lombok.Data;

import java.util.List;

@Data
public class RoomsResponse {
    private List<Room> roomDtoList;

    private Integer totalPage;

    private Integer pageNumber;
}
