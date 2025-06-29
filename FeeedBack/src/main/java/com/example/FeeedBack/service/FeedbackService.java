package com.example.FeeedBack.service;

import com.example.FeeedBack.dto.feedback.FeedBackRequest;
import com.example.FeeedBack.dto.feedback.FeedBackResponse;
import com.example.FeeedBack.exception.AccessDeniedException;
import com.example.FeeedBack.exception.DuplicateFeedbackException;
import com.example.FeeedBack.exception.TeacherNotFoundException;
import com.example.FeeedBack.model.Feedback;
import com.example.FeeedBack.model.RoleType;
import com.example.FeeedBack.model.Teacher;
import com.example.FeeedBack.model.User;
import com.example.FeeedBack.repository.FeedBackRepository;
import com.example.FeeedBack.repository.TeacherRepository;
import com.example.FeeedBack.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeedbackService {

    private final FeedBackRepository feedbackRepository;
    private final TeacherRepository teacherRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final TeacherService teacherService;

    public FeedBackResponse addFeedBack(FeedBackRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        User student = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException(MessageFormat.format("User not found for email {0}", email)));

        Teacher teacher = teacherRepository.findById(request.getTeacherId())
                .orElseThrow(() -> new RuntimeException(MessageFormat.format("Teacher not found for id {0}", email)));

        Feedback feedBack = Feedback.builder()
                .student(student)
                .teacher(teacher)
                .knowledgeRating(request.getKnowledgeRating())
                .communicationRating(request.getCommunicationRating())
                .organizationRating(request.getOrganizationRating())
                .comment(request.getComment())
                .createdAt(LocalDateTime.now())
                .build();

        feedbackRepository.save(feedBack);

        return convertToDto(feedBack);
    }

    public double calculateAvgRating(Long teacherId){
        List<Feedback> feedbacks = feedbackRepository.findByTeacherId(teacherId);
        if (feedbacks.isEmpty()){
            return 2.5;
        }

        double sum = feedbacks.stream()
                .mapToDouble(fb -> (fb.getKnowledgeRating() + fb.getCommunicationRating() + fb.getOrganizationRating()) / 3.0).sum();
        return sum / feedbacks.size();
    }

    @Transactional
    public FeedBackResponse addFeedback(FeedBackRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String studentEmail = auth.getName();
        User student = userService.findUserByEmail(studentEmail);

        if (student.getRole() != RoleType.ROLE_STUDENT) {
            throw new AccessDeniedException("Только студенты могут оставлять отзывы");
        }

        var teacherDto = teacherService.getTeacherById(request.getTeacherId());
        Teacher teacher = teacherService.convertToEntity(teacherDto);

        if (feedbackRepository.existsByStudentAndTeacher(student, teacher)) {
            throw new DuplicateFeedbackException(MessageFormat.format("{0}, {1}", student.getId(), teacher.getId()));
        }

        Feedback feedback = Feedback.builder()
                .student(student)
                .teacher(teacher)
                .knowledgeRating(request.getKnowledgeRating())
                .communicationRating(request.getCommunicationRating())
                .organizationRating(request.getOrganizationRating())
                .comment(request.getComment())
                .createdAt(LocalDateTime.now())
                .build();

        Feedback savedFeedback = feedbackRepository.save(feedback);

        return convertToDto(savedFeedback);
    }

    @Transactional(readOnly = true)
    public List<FeedBackResponse> getFeedbacksByTeacher(Long teacherId) {
        if (!teacherRepository.existsById(teacherId)) {
            throw new TeacherNotFoundException(MessageFormat.format("Teacher not found for id {0}", teacherId));
        }

        List<Feedback> feedbacks = feedbackRepository.findByTeacherIdOrderByCreatedAtDesc(teacherId);

        return feedbacks.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private FeedBackResponse convertToDto(Feedback feedback) {
        return FeedBackResponse.builder()
                .id(feedback.getId())
                .studentName(feedback.getStudent().getUsername())
                .teacherName(feedback.getTeacher().getFullName())
                .knowledgeRating(feedback.getKnowledgeRating())
                .communicationRating(feedback.getCommunicationRating())
                .organizationRating(feedback.getOrganizationRating())
                .comment(feedback.getComment())
                .createdAt(feedback.getCreatedAt())
                .build();
    }
}
