package com.example.student_management.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.student_management.dto.StudentDTO;
import com.example.student_management.entity.Student;
import com.example.student_management.repository.StudentRepository;

import jakarta.validation.Valid;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentrepository;
	
	private StudentDTO convertToDTO(Student student) {
	    StudentDTO dto = new StudentDTO();
	    dto.setName(student.getName());
	    dto.setEmail(student.getEmail());
	    dto.setAge(student.getAge());
	    return dto;
	}

	
	//method to create a student object
	public StudentDTO createStudent(StudentDTO studentDTO) {
	    // Convert DTO to Entity
	    Student student = new Student();
	    student.setName(studentDTO.getName());
	    student.setEmail(studentDTO.getEmail());
	    student.setAge(studentDTO.getAge());

	    // Save entity
	    Student savedStudent = studentrepository.save(student);

	    // Convert Entity back to DTO to return
	    StudentDTO savedDTO = new StudentDTO();
	    savedDTO.setName(savedStudent.getName());
	    savedDTO.setEmail(savedStudent.getEmail());
	    savedDTO.setAge(savedStudent.getAge());

	    return savedDTO;
	}


	
	//method to read student data
	public List<StudentDTO> getAllStudents() {
	    List<Student> students = studentrepository.findAll();
	    return students.stream()
	            .map(this::convertToDTO)
	            .collect(Collectors.toList());
	}

	
	//method to read student by id
	public StudentDTO getStudentById(int id) {
	    Student student = studentrepository.findById(id).orElse(null);
	    return convertToDTO(student);
	}

	
	//update student 
	public StudentDTO updateStudent(int id, Student updatestud) {
	    Student existing = studentrepository.findById(id).orElse(null);

	    existing.setName(updatestud.getName());
	    existing.setEmail(updatestud.getEmail());
	    existing.setAge(updatestud.getAge());

	    Student updated = studentrepository.save(existing);

	    return convertToDTO(updated);
	}

	     
	//Delete student by Id 
        public void deletStud(int id) {
		if(studentrepository.existsById(id)) {
			studentrepository.deleteById(id);
		}else {
			throw new RuntimeException("Student not fount with id" +id);
		}
	}
        
        //paginating and sorting
       
}
