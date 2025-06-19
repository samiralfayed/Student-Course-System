package com.example.studentcourse;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentcourseApplication {

	private static final Logger logger = LoggerFactory.getLogger(StudentcourseApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(StudentcourseApplication.class, args);
		logger.info("✅ Application Started Successfully");
		System.out.println("Student-Course-System Started Successfully");
	}

}
