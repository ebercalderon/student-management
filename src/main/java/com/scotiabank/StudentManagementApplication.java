package com.scotiabank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

/**
 * Clase principal de la aplicación de gestión de estudiantes..<br/>
 * <b>Class</b>: StudentManagementApplication<br/>
 */
@EnableWebFlux
@SpringBootApplication
public class StudentManagementApplication {

  public static void main(String[] args) {
    SpringApplication.run(StudentManagementApplication.class, args);
  }

}
