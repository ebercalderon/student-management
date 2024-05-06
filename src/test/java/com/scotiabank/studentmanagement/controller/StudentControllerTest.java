package com.scotiabank.studentmanagement.controller;

import com.scotiabank.studentmanagement.model.entity.Student;
import com.scotiabank.studentmanagement.service.StudentService;
import com.scotiabank.studentmanagement.util.DummyUtil;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class StudentControllerTest {

  @InjectMocks
  private StudentController controller;

  @Mock
  private StudentService service;

  private Student student;

  @BeforeEach
  void setUp() {
    student = DummyUtil.getStudent();
  }

  @DisplayName("Create student when data is complete")
  @Test
  void createStudentWhenDataIsComplete() {

    given(service.createStudent(isA(Student.class)))
        .willReturn(Mono.empty());

    Mono<Void> response = controller.createStudent(student);

    StepVerifier.create(response)
            .verifyComplete();
  }

  @DisplayName("List students when status is active")
  @Test
  void listStudentsWhenStatusIsActive() {

    given(service.listStudentByStatus(anyString()))
        .willReturn(Flux.just(student));

    Flux<Student> response = controller.listStudentByStatus();

    StepVerifier.create(response)
        //.assertNext(result -> {
        //  assertThat(result.getId().equals(student.getId()));
        //})
        .expectNext(student)
        .verifyComplete();
  }
}
