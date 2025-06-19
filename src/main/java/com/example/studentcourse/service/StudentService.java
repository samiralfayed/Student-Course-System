package com.example.studentcourse.service;

import com.example.studentcourse.model.Student;
import java.util.List;

public interface StudentService {

    Student addStudent(Student student);
    List<Student> getAllStudents();
    void enrollStudentInCourse(Long studentId, Long courseId);
}
