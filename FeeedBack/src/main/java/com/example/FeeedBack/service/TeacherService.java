package com.example.FeeedBack.service;

import com.example.FeeedBack.dto.teacher.TeacherDto;
import com.example.FeeedBack.dto.teacher.TeacherResponseDto;
import com.example.FeeedBack.exception.TeacherNotFoundException;
import com.example.FeeedBack.model.Teacher;
import com.example.FeeedBack.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final FeedbackService feedbackService;

    @Transactional
    public TeacherResponseDto createTeacher(TeacherDto dto) {
        Teacher teacher = Teacher.builder()
                .fullName(dto.getUsername())
                .department(dto.getDepartment())
                .build();

        Teacher savedTeacher = teacherRepository.save(teacher);
        return convertToDto(savedTeacher);
    }

    @Transactional(readOnly = true)
    public List<TeacherResponseDto> getAllTeachers(Pageable pageable) {
        Page<Teacher> teachers = teacherRepository.findAll(pageable);
        return teachers.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public TeacherResponseDto getTeacherById(Long id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new TeacherNotFoundException(MessageFormat.format("Teacher with id {0} not found", id)));
        return convertToDto(teacher);
    }

    @Transactional
    public TeacherResponseDto updateTeacher(Long id, TeacherDto dto) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new TeacherNotFoundException(MessageFormat.format("Teacher with id {0} not found", id)));

        teacher.setFullName(dto.getUsername());
        teacher.setDepartment(dto.getDepartment());

        Teacher updatedTeacher = teacherRepository.save(teacher);
        return convertToDto(updatedTeacher);
    }

    @Transactional
    public void deleteTeacher(Long id) {
        if (!teacherRepository.existsById(id)) {
            throw new TeacherNotFoundException(MessageFormat.format("Teacher with id {0} not found", id));
        }
        teacherRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<TeacherResponseDto> getTeachersByDepartment(String department) {
        return teacherRepository.findByDepartment(department).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private TeacherResponseDto convertToDto(Teacher teacher) {
        double avgRating = feedbackService.calculateAvgRating(teacher.getId());
        return TeacherResponseDto.builder()
                .id(teacher.getId())
                .username(teacher.getFullName())
                .department(teacher.getDepartment())
                .avgRating(avgRating)
                .build();
    }

    public Teacher convertToEntity(TeacherResponseDto dto) {
        return Teacher.builder()
                .fullName(dto.getUsername())
                .department(dto.getDepartment())
                .id(dto.getId())
                .build();
    }
}