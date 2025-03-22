package org.example.service.auth;

import org.example.dto.SignUpRequest;
import org.example.dto.User;

public interface AuthService {
    User createUser(SignUpRequest signUpRequest);
}
