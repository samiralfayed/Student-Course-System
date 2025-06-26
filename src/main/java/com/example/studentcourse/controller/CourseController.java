package com.example.studentcourse.controller;


import com.example.studentcourse.dto.CourseRequest;
import com.example.studentcourse.model.Course;
import com.example.studentcourse.model.Student;
import com.example.studentcourse.repository.StudentRepository;
import com.example.studentcourse.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/courses")
@CrossOrigin(origins = "*")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping
    public Course createCourse(@RequestBody CourseRequest request) {
        Course course = new Course();
        course.setTitle(request.getTitle());
        course.setDescription(request.getDescription());

        Set<Student> students = new HashSet<>();
        if (request.getStudentIds() != null) {
            for (Long studentId : request.getStudentIds()) {
                Student student = studentRepository.findById(studentId)
                        .orElseThrow(() -> new RuntimeException("Student not found: " + studentId));
                students.add(student);
            }
        }

        course.setStudents(students);
        Course saved = courseService.addCourse(course);
        System.out.println("✅ Course created with ID: " + saved.getId());
        return saved;
    }

    @GetMapping
    public List<Course> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        for (Course course : courses) {
            System.out.println("📚 Found Course ID: " + course.getId());
        }
        return courses;
    }

    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable Long id) {
        return courseService.getCourseById(id);
    }

    @PutMapping("/{id}")
    public Course updateCourse(@PathVariable Long id, @RequestBody CourseRequest request) {
        Course existing = courseService.getCourseById(id);
        System.out.println("🛠️ Updating Course ID: " + existing.getId());

        existing.setTitle(request.getTitle());
        existing.setDescription(request.getDescription());

        Set<Student> students = new HashSet<>();
        if (request.getStudentIds() != null) {
            for (Long studentId : request.getStudentIds()) {
                Student student = studentRepository.findById(studentId)
                        .orElseThrow(() -> new RuntimeException("Student not found: " + studentId));
                students.add(student);
            }
        }

        existing.setStudents(students);
        return courseService.updateCourse(id, existing);
    }

    @DeleteMapping("/{id}")
    public void deleteCourseById(@PathVariable Long id) {
        courseService.deleteCourseById(id);
    }

    @DeleteMapping
    public void deleteAllCourses() {
        courseService.deleteAllCourses();
    }

    @GetMapping("/{id}/students")
    public Set<Student> getEnrolledStudents(@PathVariable Long id) {
        Course course = courseService.getCourseById(id);
        return course.getStudents();
    }

    ///

    @GetMapping("/search")
    public List<Course> searchCoursesByTitle(@RequestParam String keyword) {
        return courseService.searchCoursesByTitle(keyword);
    }

    @GetMapping("/student/{studentId}")
    public List<Course> getCoursesByStudent(@PathVariable Long studentId) {
        return courseService.getCoursesByStudentId(studentId);
    }
}
