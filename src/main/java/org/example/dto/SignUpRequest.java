package org.example.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
public class SignUpRequest {
    private String email;
    private String password;
    private String name;
}
