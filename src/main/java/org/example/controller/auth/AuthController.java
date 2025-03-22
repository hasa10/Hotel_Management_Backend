package org.example.controller.auth;

import jakarta.persistence.EntityExistsException;
import org.example.dto.AuthenticationRequest;
import org.example.dto.AuthenticationResponse;
import org.example.dto.SignUpRequest;
import org.example.dto.User;
import org.example.entity.UserEntity;
import org.example.repository.UserDao;
import org.example.service.auth.AuthService;
import org.example.service.jwt.UserService;
import org.example.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final UserDao dao;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signupUser(@RequestBody SignUpRequest signupRequest) {
        System.out.println(signupRequest);
        try {
            User createdUser = authService.createUser(signupRequest);
            return new ResponseEntity<>(createdUser, HttpStatus.OK);
        } catch (EntityExistsException entityExistsException) {
            return new ResponseEntity<>("User already exists", HttpStatus.NOT_ACCEPTABLE);
        } catch (Exception e) {
            return new ResponseEntity<>("User not created, come again later", HttpStatus.BAD_REQUEST);
                    }
                }

    @PostMapping("/login")
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect username or password");
        }
        final UserDetails userDetails = userService.userDetailsService().loadUserByUsername(request.getEmail());
        Optional<UserEntity> optionalUser =dao.findByEmail(userDetails.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        if (optionalUser.isPresent()) {
            authenticationResponse.setJwt(jwt);
            authenticationResponse.setRole(optionalUser.get().getUserRole());
            authenticationResponse.setUserId(optionalUser.get().getId());
        }else{
            System.out.println("User not found: " + request.getEmail());
        }
        return authenticationResponse;
    }
}