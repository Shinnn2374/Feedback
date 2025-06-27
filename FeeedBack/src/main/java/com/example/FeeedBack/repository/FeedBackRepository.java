package com.example.FeeedBack.repository;

import com.example.FeeedBack.model.FeedBack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedBackRepository extends JpaRepository<FeedBack, Long> {

    List<FeedBack> findByTeacherId(Long teacherId);
}
