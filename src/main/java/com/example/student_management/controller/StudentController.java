package com.example.student_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.student_management.interfaceservice.StudentService;
import com.example.student_management.dto.StudentDTO;
import com.example.student_management.entity.Student;
import com.example.student_management.repository.StudentRepository;


import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class StudentController { 

	@GetMapping("/ping")
	public String ping() {
	    return "App is working!";
	}

	@Autowired
	private StudentService studentservice;

	
	//create student mapping handeller
	@PostMapping("/students")
	public ResponseEntity<String> createStudent(@Valid @RequestBody StudentDTO studentdto) {
		
	 studentservice.createStudent(studentdto);
	 return ResponseEntity.ok("saved student");
	}
	
	//get all student

	@GetMapping("/students")
	public ResponseEntity<Page<StudentDTO>> getAllStudents(
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "5") int size,
	        @RequestParam(defaultValue = "name") String sortField,
	        @RequestParam(defaultValue = "asc") String sortDir) {
	    
		Page<StudentDTO> students = studentservice.getAllStudents(page, size, sortField, sortDir);
		return ResponseEntity.ok(students);

	}


	
	//get student by id 
	@GetMapping("/students/{id}")
	public StudentDTO getStudentById(@PathVariable int id) {
		return studentservice.getStudentById(id);
	}
	
	//update student 
	@PutMapping("/students/{id}")
	public StudentDTO UpdateStud(@PathVariable int id , @RequestBody Student Updatestud) {
		return studentservice.updateStudent(id, Updatestud);
	}
	
	//delete student by id 
	@DeleteMapping("/students/{id}")
	public void DeleteStudentById(@PathVariable Integer id) {
		studentservice.deletStud(id);
	}
	
}
