package org.example.dto;

import lombok.Data;

@Data
public class Room {

    private Long id;

    private String name;

    private String type;

    private Long price;

    private Boolean available;

}
