package com.example.FeeedBack.service;

import com.example.FeeedBack.dto.user.UserProfileDto;
import com.example.FeeedBack.exception.UsernameNotFoundException;
import com.example.FeeedBack.model.User;
import com.example.FeeedBack.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserProfileDto getUserProfile(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return UserProfileDto.builder()
                .email(user.getEmail())
                .username(user.getUsername())
                .role(user.getRole())
                .build();
    }
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }
}
