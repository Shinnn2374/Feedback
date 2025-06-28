package com.example.FeeedBack.dto.user;

import com.example.FeeedBack.dto.feedback.FeedBackResponse;
import com.example.FeeedBack.model.RoleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfileDto {

    private String email;

    private String username;

    private RoleType role;

    private List<FeedBackResponse> feedbacks;
}
