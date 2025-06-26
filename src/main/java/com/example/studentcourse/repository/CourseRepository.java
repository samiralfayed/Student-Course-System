package com.example.studentcourse.repository;


import com.example.studentcourse.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    // Find courses by title containing a keyword (search)
    List<Course> findByTitleContainingIgnoreCase(String keyword);

    // Get all courses a specific student is enrolled in
    List<Course> findByStudents_Id(Long studentId);
}
