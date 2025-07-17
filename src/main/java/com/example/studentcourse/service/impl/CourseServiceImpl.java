package com.example.studentcourse.service.impl;

import com.example.studentcourse.model.Course;
import com.example.studentcourse.repository.CourseRepository;
import com.example.studentcourse.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepo;

    @Override
    public Course addCourse(Course course) {
        course.setId(null);
        return courseRepo.save(course);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepo.findAll();
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found with ID: " + id));
    }

    @Override
    public Course updateCourse(Long id, Course updatedCourse) {
        Course existing = getCourseById(id);
        existing.setTitle(updatedCourse.getTitle());
        existing.setDescription(updatedCourse.getDescription());
        existing.setStudents(updatedCourse.getStudents()); // ✅ fix: update students as well
        return courseRepo.save(existing);
    }

    @Override
    public void deleteCourseById(Long id) {
        if (!courseRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found with ID: " + id);
        }
        courseRepo.deleteById(id);
    }

    @Override
    public void deleteAllCourses() {
        courseRepo.deleteAll();
    }


    @Override
    public List<Course> searchCoursesByTitle(String keyword) {
        return courseRepo.findByTitleContainingIgnoreCase(keyword);
    }

    @Override
    public List<Course> getCoursesByStudentId(Long studentId) {
        return courseRepo.findByStudents_Id(studentId);
    }
}
