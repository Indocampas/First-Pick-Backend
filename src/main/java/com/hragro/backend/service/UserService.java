package com.hragro.backend.service;

import com.hragro.backend.model.User;
import com.hragro.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public User createAdminUser(String username, String password) {
        if (userRepository.existsByUsername(username)) {
            throw new RuntimeException("User already exists");
        }
        
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole("ADMIN");
        user.setActive(true);
        
        return userRepository.save(user);
    }
}