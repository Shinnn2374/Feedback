package com.example.FeeedBack.service;

import com.example.FeeedBack.dto.user.UserManagerDto;
import com.example.FeeedBack.exception.AccessDeniedException;
import com.example.FeeedBack.exception.IllegalRoleChangeException;
import com.example.FeeedBack.exception.UserNotFoundException;
import com.example.FeeedBack.model.Feedback;
import com.example.FeeedBack.model.RoleType;
import com.example.FeeedBack.model.User;
import com.example.FeeedBack.repository.FeedBackRepository;
import com.example.FeeedBack.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminService {

    private final FeedBackRepository feedBackRepository;
    private final UserRepository userRepository;

    public void deleteFeedback(Long feedbackId) {
        feedBackRepository.deleteById(feedbackId);
    }

    public List<Feedback> getAllFeedBack() {
        return feedBackRepository.findAll();
    }

    @Transactional
    public void changeUserRole(UserManagerDto dto) {
        String currentAdminEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User admin = userRepository.findByEmail(currentAdminEmail)
                .orElseThrow(() -> new UserNotFoundException(currentAdminEmail));

        if (admin.getRole() != RoleType.ADMIN) {
            throw new AccessDeniedException("Только ADMIN может изменять роли пользователей");
        }

        User userToUpdate = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new UserNotFoundException(currentAdminEmail));

        if (userToUpdate.getRole() == RoleType.ADMIN) {
            throw new IllegalRoleChangeException("Нельзя изменять роль другого ADMIN");
        }

        if (dto.getRole() == null || dto.getRole() == RoleType.ADMIN) {
            throw new IllegalRoleChangeException("Недопустимая роль: " + dto.getRole());
        }

        userToUpdate.setRole(dto.getRole());
        userRepository.save(userToUpdate);

        log.info("ADMIN {} изменил роль пользователя {} с {} на {}",
                admin.getId(),
                userToUpdate.getId(),
                userToUpdate.getRole(),
                dto.getRole());
    }
}
