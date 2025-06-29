package com.example.FeeedBack.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "feedbacks")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User student;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Teacher teacher;

    @Column(nullable = false)
    private int knowledgeRating;

    @Column(nullable = false)
    private int communicationRating;

    @Column(nullable = false)
    private int organizationRating;

    private String comment;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
