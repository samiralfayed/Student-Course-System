package com.example.studentcourse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.studentcourse.model.Student;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>  {
}
