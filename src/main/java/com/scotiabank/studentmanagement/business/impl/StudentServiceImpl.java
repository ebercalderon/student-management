package com.scotiabank.studentmanagement.business.impl;

import com.scotiabank.studentmanagement.business.StudentService;
import com.scotiabank.studentmanagement.exception.ApiException;
import com.scotiabank.studentmanagement.model.entity.Student;
import com.scotiabank.studentmanagement.model.enums.ErrorCatalog;
import com.scotiabank.studentmanagement.repository.StudentRepository;
import com.scotiabank.studentmanagement.util.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Servicio para gestionar operaciones relacionadas con los estudiantes.<br/>
 * <b>Class</b>: StudentService<br/>
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class StudentServiceImpl implements StudentService {

  private final StudentRepository studentRepository;

  @Override
  public Flux<Student> listStudentByStatus(String status) {
    log.info("Initiating listing students by status");
    return studentRepository.listStudentByStatus(status);
  }

  @Override
  public Mono<Void> createStudent(Student student) {
    log.info("Initiating creating new student");
    return this.findById(student.getId())
        .flatMap(existingAlumno -> {
          log.error("Failed to create student: Student with ID {} already exists.",
              Utils.obfuscateInfo(student.getId()));
          return Mono.error(new ApiException(ErrorCatalog.E002));
        })
        .switchIfEmpty(
            studentRepository.createStudent(student.getId(), student.getName(),
                student.getLastName(), student.getStatus().toUpperCase(), student.getAge())
        ).then();
  }

  private Mono<Student> findById(String id) {
    return studentRepository.findById(id);
  }
}
