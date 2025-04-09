package com.example.student_management.Impl;

import com.example.student_management.dto.StudentDTO;
import com.example.student_management.entity.Student;
import com.example.student_management.interfaceservice.StudentService;
import com.example.student_management.mapper.StudentMapper;
import com.example.student_management.repository.StudentRepository;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void createStudent(StudentDTO studentDTO) {
        Student student = StudentMapper.toEntity(studentDTO);
        studentRepository.save(student);
    }

    @Override
    public StudentDTO getStudentById(int id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
        return StudentMapper.toDTO(student);
    }

    @Override
    public StudentDTO updateStudent(int id, Student updateStudent) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
        student.setName(updateStudent.getName());
        student.setAge(updateStudent.getAge());
        student.setEmail(updateStudent.getEmail());
        Student saved = studentRepository.save(student);
        return StudentMapper.toDTO(saved);
    }

    @Override
    public void deletStud(int id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Page<StudentDTO> getAllStudents(int page, int size, String sortField, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Student> studentPage = studentRepository.findAll(pageable);
        return studentPage.map(StudentMapper::toDTO);
    }
}
