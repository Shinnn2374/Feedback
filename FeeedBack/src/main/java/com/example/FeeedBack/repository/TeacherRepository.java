package com.example.FeeedBack.repository;

import com.example.FeeedBack.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    List<Teacher> findByTeacherDepartment(String department);

    List<Teacher> findByDepartment(String department);
}
