package com.example.FeeedBack.controller;

import com.example.FeeedBack.dto.teacher.TeacherDto;
import com.example.FeeedBack.dto.teacher.TeacherResponseDto;
import com.example.FeeedBack.service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public ResponseEntity<List<TeacherResponseDto>> getAllTeachers(Pageable pageable) {
        return ResponseEntity.ok(teacherService.getAllTeachers(pageable));
    }

    @PostMapping
    public ResponseEntity<TeacherResponseDto> createTeacher(
            @Valid @RequestBody TeacherDto dto
    ) {
        return ResponseEntity.ok(teacherService.createTeacher(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherResponseDto> getTeacherById(@PathVariable Long id) {
        return ResponseEntity.ok(teacherService.getTeacherById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherResponseDto> updateTeacher(
            @PathVariable Long id,
            @Valid @RequestBody TeacherDto dto
    ) {
        return ResponseEntity.ok(teacherService.updateTeacher(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/department/{department}")
    public ResponseEntity<List<TeacherResponseDto>> getByDepartment(
            @PathVariable String department
    ) {
        return ResponseEntity.ok(teacherService.getTeachersByDepartment(department));
    }

    @GetMapping("/teachers")
    public String listTeachers(Model model) {
        teacherService.getAllTeachers(Pageable.unpaged());
        return "teacher/list";
    }

    @GetMapping("/teachers/{id}")
    public String teacherDetails(@PathVariable Long id, Model model) {
        teacherService.getTeacherById(id);
        return "teacher/detail";
    }
}