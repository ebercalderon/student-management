package com.scotiabank.studentmanagement.service;

import com.scotiabank.studentmanagement.model.Student;
import com.scotiabank.studentmanagement.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Servicio para gestionar operaciones relacionadas con los estudiantes.<br/>
 * <b>Class</b>: StudentService<br/>
 */
@Service
@RequiredArgsConstructor
public class StudentService {

  private final StudentRepository alumnoRepository;

  public Flux<Student> listStudentByStatus(String status) {
    return alumnoRepository.listStudentByStatus(status);
  }

  public Mono<Student> createStudent(Student student) {
    return alumnoRepository.createStudent(student.getId(), student.getName(), student.getLastName(),
        student.getStatus(), student.getAge());
  }

  public Mono<Student> findById(String id) {
    return alumnoRepository.findById(id);
  }
}
