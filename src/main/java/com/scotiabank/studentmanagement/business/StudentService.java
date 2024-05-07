package com.scotiabank.studentmanagement.business;

import com.scotiabank.studentmanagement.model.entity.Student;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Interface del recurso Student.<br/>
 * <b>Class</b>: StudentService<br/>
 */
public interface StudentService {

  Flux<Student> listStudentByStatus(String status);

  Mono<Void> createStudent(Student student);

}
