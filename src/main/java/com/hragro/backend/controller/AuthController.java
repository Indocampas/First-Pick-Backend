package com.hragro.backend.controller;

import com.hragro.backend.dto.AuthRequest;
import com.hragro.backend.dto.AuthResponse;
import com.hragro.backend.dto.ResponseMessage;
import com.hragro.backend.model.User;
import com.hragro.backend.security.JwtUtil;
import com.hragro.backend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    authRequest.getUsername(),
                    authRequest.getPassword()
                )
            );

            String token = jwtUtil.generateToken(authRequest.getUsername(), "ADMIN");
            return ResponseEntity.ok(new AuthResponse(token, authRequest.getUsername(), "Login successful"));

        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ResponseMessage("Invalid credentials", false));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody AuthRequest authRequest) {
        try {
            User user = userService.createAdminUser(authRequest.getUsername(), authRequest.getPassword());
            return ResponseEntity.ok(new ResponseMessage("User created successfully", true));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ResponseMessage(e.getMessage(), false));
        }
    }

    @GetMapping("/verify")
    public ResponseEntity<?> verifyToken(@RequestHeader(value = "Authorization", required = false) String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            try {
                String username = jwtUtil.extractUsername(token);
                if (username != null && !jwtUtil.isTokenExpired(token)) {
                    return ResponseEntity.ok(new ResponseMessage("Token valid", true));
                }
            } catch (Exception e) {
                return ResponseEntity.badRequest()
                        .body(new ResponseMessage("Invalid token", false));
            }
        }
        return ResponseEntity.badRequest()
                .body(new ResponseMessage("Invalid token", false));
    }
}