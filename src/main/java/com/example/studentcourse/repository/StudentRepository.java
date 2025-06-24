package com.example.studentcourse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.studentcourse.model.Student;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>  {

    // ✅ Check if a student with a given email exists (used to prevent duplicates)
    Optional<Student> findByEmail(String email);

    // 🆕 Optional: Find students whose name contains a substring (useful for search)
    List<Student> findByNameContainingIgnoreCase(String keyword);

    // 🆕 Optional: List all students enrolled in a specific course
    List<Student> findByCourses_Id(Long courseId);
}
