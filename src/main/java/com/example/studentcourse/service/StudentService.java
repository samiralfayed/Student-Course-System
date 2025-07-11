package com.example.studentcourse.service;

import com.example.studentcourse.model.Student;
import java.util.List;

public interface StudentService {
    Student addStudent(Student student);
    Student updateStudent(Student student);
    List<Student> getAllStudents();
    void enrollStudentInCourse(Long studentId, Long courseId);
    void deleteStudentById(Long id);
    void deleteAllStudents();
    List<Student> searchStudentsByName(String keyword);
    List<Student> getStudentsByCourseId(Long courseId);
    Student getStudentById(Long id);
}
