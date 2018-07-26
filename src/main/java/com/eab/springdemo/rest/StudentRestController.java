package com.eab.springdemo.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.omg.PortableServer.ThreadPolicyOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import com.eab.springdemo.entity.Student;

@RestController
@RequestMapping("api")
public class StudentRestController {

	private List<Student> students;

	@PostConstruct
	public void loadData() {

		students = new ArrayList<>();
		students.add(new Student("fname_1", "lastname_1"));
		students.add(new Student("fname_2", "lastname_2"));
		students.add(new Student("fname_3", "lastname_3"));

		return;
	}

	// define an end point to /students
	@GetMapping("/students")
	public List<Student> getStudents() {

		return this.students;
	}

	@GetMapping("/students/{studentId}")
	public Student getStudent(@PathVariable int studentId) {

		if ((studentId < 0) || (studentId >= this.students.size())) {
			throw new StudentNotFoundException("Student id not found - " + studentId);
		}

		return this.students.get(studentId);
	}

}
