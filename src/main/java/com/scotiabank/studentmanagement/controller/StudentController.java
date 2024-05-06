package com.scotiabank.studentmanagement.controller;

import com.scotiabank.studentmanagement.model.Student;
import com.scotiabank.studentmanagement.service.StudentService;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Controlador para manejar las operaciones relacionadas con los estudiantes.<br/>
 * <b>Class</b>: StudentController<br/>
 */
@RestController
@RequestMapping("/students")
@Slf4j
@RequiredArgsConstructor
public class StudentController {

  private static final String ESTADO_ACTIVO = "activo";
  private final StudentService studentService;

  /**
   * Crea un nuevo estudiante.
   *
   * @param student el estudiante a crear
   * @return un Mono que representa al estudiante creado
   */
  @PostMapping
  public Mono<Object> crearAlumno(@Valid @RequestBody Student student) {
    return studentService.findById(student.getId()).map(Optional::of)
        .defaultIfEmpty(Optional.empty())
        .flatMap(existingAlumno -> {
          if (existingAlumno.isPresent()) {
            return Mono.error(new ValidationException("Ya existe un alumno con ese ID"));
          } else {
            return studentService.createStudent(student)
                .then(Mono.just(ResponseEntity.status(HttpStatus.CREATED)
                    .body(ServerResponse.status(HttpStatus.CREATED)
                        .bodyValue("Alumno creado exitosamente"))));
          }

        });
  }

  /**
   * Obtiene una lista de estudiantes activos.
   *
   * @return una secuencia de estudiantes activos
   */
  @GetMapping
  public Flux<Student> obtenerAlumnosActivos() {
    return studentService.listStudentByStatus(ESTADO_ACTIVO);
  }

}
