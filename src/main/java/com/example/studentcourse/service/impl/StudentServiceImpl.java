package com.example.studentcourse.service.impl;


import com.example.studentcourse.model.Course;
import com.example.studentcourse.model.Student;
import com.example.studentcourse.repository.CourseRepository;
import com.example.studentcourse.repository.StudentRepository;
import com.example.studentcourse.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private CourseRepository courseRepo;

    @Override
    public Student addStudent(Student student) {
        Optional<Student> existing = studentRepo.findByEmail(student.getEmail());
        if (existing.isPresent()) {
            throw new RuntimeException("Email already registered.");
        }
        return studentRepo.save(student);
    }

    @Override
    public Student updateStudent(Student student) {
        Optional<Student> existing = studentRepo.findByEmail(student.getEmail());
        if (existing.isPresent() && !existing.get().getId().equals(student.getId())) {
            throw new RuntimeException("Email already registered.");
        }
        return studentRepo.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    @Override
    @Transactional
    public void enrollStudentInCourse(Long studentId, Long courseId) {
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + studentId));
        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with ID: " + courseId));

        if (!student.getCourses().contains(course)) {
            student.getCourses().add(course);
            studentRepo.save(student);
        }
    }

    @Override
    public void deleteStudentById(Long id) {
        studentRepo.deleteById(id);
    }

    @Override
    public void deleteAllStudents() {
        studentRepo.deleteAll();
    }

    @Override
    public List<Student> searchStudentsByName(String keyword) {
        return studentRepo.findByNameContainingIgnoreCase(keyword);
    }

    @Override
    public List<Student> getStudentsByCourseId(Long courseId) {
        return studentRepo.findByCourses_Id(courseId);
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));
    }
}
