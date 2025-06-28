package com.example.FeeedBack.service;

import com.example.FeeedBack.dto.feedback.FeedBackResponse;
import com.example.FeeedBack.dto.teacher.TeacherDto;
import com.example.FeeedBack.dto.teacher.TeacherResponseDto;
import com.example.FeeedBack.model.FeedBack;
import com.example.FeeedBack.model.Teacher;
import com.example.FeeedBack.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final FeedbackService feedbackService;

    public Teacher createTeacher(TeacherDto dto) {
        Teacher teacher = Teacher.builder()
                .fullName(dto.getUsername())
                .department(dto.getDepartment())
                .build();
        return teacherRepository.save(teacher);
    }

    public List<TeacherResponseDto> getAllTeachers() {
        return teacherRepository.findAll().stream()
                .map(teacher -> {
                    double avgRating = feedbackService.calculateAvgRating(teacher.getId());
                    return TeacherResponseDto.builder()
                            .id(teacher.getId())
                            .username(teacher.getFullName())
                            .department(teacher.getDepartment())
                            .avgRating(avgRating)
                            .build();
                }).collect(Collectors.toList());
    }

    private FeedBackResponse convertToDto(FeedBack feedBack){
        return FeedBackResponse.builder()
                .id(feedBack.getId())
                .studentName(feedBack.getUser().getUsername())
                .teacherName(feedBack.getTeacher().getFullName())
                .knowledgeRating(feedBack.getKnowledgeRating())
                .communicationRating(feedBack.getCommunicationRating())
                .organizationRating(feedBack.getOrganizationRating())
                .comment(feedBack.getComment())
                .createdAt(feedBack.getCreatedAt())
                .build();
    }
}
