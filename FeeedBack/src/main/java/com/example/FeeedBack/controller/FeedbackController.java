package com.example.FeeedBack.controller;

import com.example.FeeedBack.dto.feedback.FeedBackRequest;
import com.example.FeeedBack.dto.feedback.FeedBackResponse;
import com.example.FeeedBack.service.FeedbackService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/feedback")
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping
    public ResponseEntity<FeedBackResponse> addFeedback(
            @Valid @RequestBody FeedBackRequest request,
            Authentication authentication
    ) {
        return ResponseEntity.ok(feedbackService.addFeedback(request));
    }

    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<List<FeedBackResponse>> getFeedbacksByTeacher(
            @PathVariable Long teacherId
    ) {
        return ResponseEntity.ok(feedbackService.getFeedbacksByTeacher(teacherId));
    }
}