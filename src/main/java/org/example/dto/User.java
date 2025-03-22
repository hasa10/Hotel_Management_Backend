package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.example.enums.UserRole;

@Data
public class User {
    private Long id;
    private String email;
    private String name;
    private UserRole userRole;
    private String password;
}
