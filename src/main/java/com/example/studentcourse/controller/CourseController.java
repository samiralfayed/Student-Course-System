package com.example.studentcourse.controller;


import com.example.studentcourse.model.Course;
import com.example.studentcourse.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/api/courses")
@CrossOrigin(origins = "*")
public class CourseController {

    @Autowired
    private CourseRepository courseRepo;

    @PostMapping
    public Course addCourse(@RequestBody Course course) {
        course.setId(null); // ensure create mode
        return courseRepo.save(course);
    }

    @GetMapping
    public List<Course> getAllCourses() {
        return courseRepo.findAll();
    }

}
