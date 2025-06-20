package com.example.studentcourse.controller;

import com.example.studentcourse.dto.StudentRequest;
import com.example.studentcourse.model.Course;
import com.example.studentcourse.model.Student;
import com.example.studentcourse.repository.CourseRepository;
import com.example.studentcourse.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "*")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseRepository courseRepo;

    @PostMapping
    public Student addStudent(@RequestBody StudentRequest request) {
        Student student = new Student();
        student.setName(request.getName());
        student.setEmail(request.getEmail());

        Set<Course> courseSet = new HashSet<>();
        for (Long courseId : request.getCourseIds()) {
            Course course = courseRepo.findById(courseId)
                    .orElseThrow(() -> new RuntimeException("Course not found with ID: " + courseId));
            courseSet.add(course);
        }
        student.setCourses(courseSet);
        return studentService.addStudent(student);
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudentById(id);
    }
}
