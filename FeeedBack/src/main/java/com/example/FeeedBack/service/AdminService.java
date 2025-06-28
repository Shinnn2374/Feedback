package com.example.FeeedBack.service;

import com.example.FeeedBack.model.FeedBack;
import com.example.FeeedBack.repository.FeedBackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final FeedBackRepository feedBackRepository;

    public void deleteFeedback(Long feedbackId) {
        feedBackRepository.deleteById(feedbackId);
    }

    public List<FeedBack> getAllFeedBack() {
        return feedBackRepository.findAll();
    }
}
