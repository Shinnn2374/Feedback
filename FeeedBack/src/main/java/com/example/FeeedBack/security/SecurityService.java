package com.example.FeeedBack.security;

import com.example.FeeedBack.entity.User;
import com.example.FeeedBack.model.CreateUserRequest;
import com.example.FeeedBack.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public void register(CreateUserRequest createUserRequest) {
        var user = User.builder()
                .username(createUserRequest.getUsername())
                .email(createUserRequest.getEmail())
                .password(passwordEncoder.encode(createUserRequest.getPassword()))
                .build();
        user.setRoles(createUserRequest.getRoles());

        userRepository.save(user);
    }
}
