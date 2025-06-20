package com.example.studentcourse.dto;


import java.util.Set;

public class StudentRequest {
    private String name;
    private String email;
    private Set<Long> courseIds;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Set<Long> getCourseIds() { return courseIds; }
    public void setCourseIds(Set<Long> courseIds) { this.courseIds = courseIds; }

}
