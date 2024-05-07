package com.scotiabank.expose.web;

import com.scotiabank.studentmanagement.business.StudentService;
import com.scotiabank.studentmanagement.model.entity.Student;
import com.scotiabank.studentmanagement.util.constants.Constants;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Controlador para manejar las operaciones relacionadas con los estudiantes.<br/>
 * <b>Class</b>: StudentController<br/>
 */
@RestController
@RequestMapping("/api/students")
@AllArgsConstructor
@Slf4j
public class StudentController {

  private final StudentService studentService;

  /**
   * Crea un nuevo estudiante.
   */
  @PostMapping
  @ResponseStatus(HttpStatus.OK)
  public Mono<Void> createStudent(@Valid @RequestBody Student student) {
    return studentService.createStudent(student);
  }

  /**
   * Obtiene una lista de estudiantes activos.
   */
  @GetMapping
  public Flux<Student> listStudentByStatus() {
    return studentService.listStudentByStatus(Constants.ACTIVE_STATUS);
  }

}
