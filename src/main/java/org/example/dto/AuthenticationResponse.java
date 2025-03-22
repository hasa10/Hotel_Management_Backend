package org.example.dto;

import lombok.Data;
import org.example.enums.UserRole;

@Data
public class AuthenticationResponse {
    private String jwt;
    private Long userId;
    private UserRole role;
}
