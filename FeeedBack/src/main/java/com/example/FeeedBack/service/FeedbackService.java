package com.example.FeeedBack.service;

import com.example.FeeedBack.dto.feedback.FeedBackRequest;
import com.example.FeeedBack.dto.feedback.FeedBackResponse;
import com.example.FeeedBack.model.FeedBack;
import com.example.FeeedBack.model.Teacher;
import com.example.FeeedBack.model.User;
import com.example.FeeedBack.repository.FeedBackRepository;
import com.example.FeeedBack.repository.TeacherRepository;
import com.example.FeeedBack.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackService {

    private final FeedBackRepository feedBackRepository;
    private final TeacherRepository teacherRepository;
    private final UserRepository userRepository;

    public FeedBackResponse addFeedBack(FeedBackRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        User student = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException(MessageFormat.format("User not found for email {0}", email)));

        Teacher teacher = teacherRepository.findById(request.getTeacherId())
                .orElseThrow(() -> new RuntimeException(MessageFormat.format("Teacher not found for id {0}", email)));

        FeedBack feedBack = FeedBack.builder()
                .user(student)
                .teacher(teacher)
                .knowledgeRating(request.getKnowledgeRating())
                .communicationRating(request.getCommunicationRating())
                .organizationRating(request.getOrganizationRating())
                .comment(request.getComment())
                .createdAt(LocalDateTime.now())
                .build();

        feedBackRepository.save(feedBack);

        return convertToDto(feedBack);
    }

    public double calculateAvgRating(Long teacherId){
        List<FeedBack> feedBacks = feedBackRepository.findByTeacherId(teacherId);
        if (feedBacks.isEmpty()){
            return 2.5;
        }

        double sum = feedBacks.stream()
                .mapToDouble(fb -> (fb.getKnowledgeRating() + fb.getCommunicationRating() + fb.getOrganizationRating()) / 3.0).sum();
        return sum / feedBacks.size();
    }

    private FeedBackResponse convertToDto(FeedBack feedBack) {

    }
}
