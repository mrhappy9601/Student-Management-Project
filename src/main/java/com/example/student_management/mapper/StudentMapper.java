package com.example.student_management.mapper;

import com.example.student_management.dto.StudentDTO;
import com.example.student_management.entity.Student;

public class StudentMapper {

    public static Student toEntity(StudentDTO dto) {
        Student student = new Student();
        student.setName(dto.getName());
        student.setEmail(dto.getEmail()); 
        student.setAge(dto.getAge());
        return student;
    }

    public static StudentDTO toDTO(Student student) {
        StudentDTO dto = new StudentDTO();
        dto.setName(student.getName());
        dto.setEmail(student.getEmail()); 
        dto.setAge(student.getAge());
        return dto;
    }
}
