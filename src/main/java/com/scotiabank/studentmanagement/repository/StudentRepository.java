package com.scotiabank.studentmanagement.repository;

import com.scotiabank.studentmanagement.model.entity.Student;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Repositorio para realizar operaciones de base de datos relacionadas con los estudiantes.<br/>
 * <b>Class</b>: StudentRepository<br/>
 */
@Repository
public interface StudentRepository extends R2dbcRepository<Student, String> {

  @Query("SELECT * FROM student WHERE status = :status")
  Flux<Student> listStudentByStatus(String status);


  @Modifying
  @Query(value = "insert into student(id, name, last_name, status, age)"
      + " VALUES (:id, :name, :lastName, :status, :age)")
  @Transactional
  Mono<Student> createStudent(@Param("id") String id,
      @Param("name") String name,
      @Param("lastName") String lastName,
      @Param("status") String status,
      @Param("age") Integer age);
}
