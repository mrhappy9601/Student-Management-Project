package com.example.student_management.interfaceservice;

import com.example.student_management.dto.StudentDTO;
import com.example.student_management.entity.Student;
import org.springframework.data.domain.Page;

public interface StudentService {

    void createStudent(StudentDTO studentDTO);

    StudentDTO getStudentById(int id);

    StudentDTO updateStudent(int id, Student student);

    void deletStud(int id); // <-- make sure this exists

    Page<StudentDTO> getAllStudents(int page, int size, String sortField, String sortDir);
}
