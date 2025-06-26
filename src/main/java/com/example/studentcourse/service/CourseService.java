package com.example.studentcourse.service;

import com.example.studentcourse.model.Course;

import java.util.List;

public interface CourseService {

    Course addCourse(Course course);
    List<Course> getAllCourses();
    Course getCourseById(Long id);
    Course updateCourse(Long id, Course updatedCourse);
    void deleteCourseById(Long id);
    void deleteAllCourses();


    ////
    List<Course> searchCoursesByTitle(String keyword);
    List<Course> getCoursesByStudentId(Long studentId);
}
