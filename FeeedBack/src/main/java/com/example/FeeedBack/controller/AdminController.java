package com.example.FeeedBack.controller;

import com.example.FeeedBack.dto.user.UserManagerDto;
import com.example.FeeedBack.service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final AdminService adminService;

    @DeleteMapping("/feedback/{id}")
    public ResponseEntity<Void> deleteFeedback(
            @PathVariable Long id
    ) {
        adminService.deleteFeedback(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/users/role")
    public ResponseEntity<Void> changeUserRole(
            @Valid @RequestBody UserManagerDto dto
    ) {
        adminService.changeUserRole(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/admin/dashboard")
    public String adminDashboard() {
        return "admin/dashboard";
    }
}