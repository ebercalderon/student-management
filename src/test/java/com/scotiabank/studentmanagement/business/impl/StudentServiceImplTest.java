package com.scotiabank.studentmanagement.business.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

import com.scotiabank.studentmanagement.exception.ApiException;
import com.scotiabank.studentmanagement.model.entity.Student;
import com.scotiabank.studentmanagement.repository.StudentRepository;
import com.scotiabank.util.DummyUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


@ExtendWith({MockitoExtension.class})
class StudentServiceImplTest {

  @Mock
  private StudentRepository repository;

  @InjectMocks
  private StudentServiceImpl service;

  private Student student;

  @BeforeEach
  void setUp() {
    student = DummyUtil.getStudent();
  }

  @DisplayName("Create student when data is valid")
  @Test
  void createStudentWhenDataIsValid() {

    given(repository.findById(anyString()))
        .willReturn(Mono.empty());
    given(repository.createStudent(anyString(), anyString(), anyString(), anyString(), anyInt()))
        .willReturn(Mono.empty());

    Mono<Void> response = service.createStudent(student);

    StepVerifier.create(response)
        .verifyComplete();
  }

  @DisplayName("List students when status is active")
  @Test
  void listStudentsWhenStatusIsActive() {
    String status = "ACTIVO";

    given(repository.listStudentByStatus(anyString()))
        .willReturn(Flux.just(student));

    Flux<Student> response = service.listStudentByStatus(status);

    StepVerifier.create(response)
        .assertNext(result -> {
          assertThat(result.getStatus()).isEqualTo(status);
        })
        .verifyComplete();
  }

  @DisplayName("Return error when id is duplicated")
  @Test
  void returnErrorWhenIdIsDuplicated() {

    given(repository.findById(anyString()))
        .willReturn(Mono.just(student));
    given(repository.createStudent(anyString(), anyString(), anyString(), anyString(), anyInt()))
        .willReturn(Mono.empty());

    Mono<Void> response = service.createStudent(student);

    StepVerifier.create(response)
        .expectError(ApiException.class)
        .verify();
  }

}
