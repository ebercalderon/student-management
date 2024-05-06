package com.scotiabank.studentmanagement.service;

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
public class StudentService {

  private final StudentRepository studentRepository;

  public Flux<Student> listStudentByStatus(String status) {
    return studentRepository.listStudentByStatus(status);
  }

  public Mono<Void> createStudent(Student student) {
    return this.findById(student.getId())
        .flatMap(existingAlumno -> Mono.error(new RuntimeException("El ID del alumno ya existe")))
        .switchIfEmpty(
            studentRepository.createStudent(student.getId(), student.getName(), student.getLastName(),
                    student.getStatus(), student.getAge())
        ).then(Mono.empty());
  }

  public Mono<Student> findById(String id) {
    return studentRepository.findById(id);
  }
}
