package com.scotiabank.studentmanagement.business.impl;

import com.scotiabank.studentmanagement.business.StudentService;
import com.scotiabank.studentmanagement.model.entity.Student;
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
public class StudentServiceImpl implements StudentService {

  private final StudentRepository studentRepository;

  @Override
  public Flux<Student> listStudentByStatus(String status) {
    return studentRepository.listStudentByStatus(status);
  }

  @Override
  public Mono<Void> createStudent(Student student) {
    return this.findById(student.getId())
        .flatMap(existingAlumno -> Mono.error(new RuntimeException("El ID del alumno ya existe")))
        .switchIfEmpty(
            studentRepository.createStudent(student.getId(), student.getName(),
                student.getLastName(), student.getStatus().toUpperCase(), student.getAge())
        ).then(Mono.empty());
  }

  private Mono<Student> findById(String id) {
    return studentRepository.findById(id);
  }
}
