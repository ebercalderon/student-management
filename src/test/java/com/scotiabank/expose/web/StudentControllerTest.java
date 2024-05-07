package com.scotiabank.expose.web;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scotiabank.studentmanagement.business.StudentService;
import com.scotiabank.studentmanagement.exception.ApiExceptionHandler;
import com.scotiabank.studentmanagement.model.entity.Student;
import com.scotiabank.util.DummyUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.MethodArgumentNotValidException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class StudentControllerTest {

  @InjectMocks
  private StudentController controller;

  @Mock
  private StudentService service;

  private Student student;

  private MockMvc mockMvc;

  private ObjectMapper objectMapper;

  @BeforeEach
  void setUp() {
    student = DummyUtil.getStudent();
  }

  @DisplayName("Return complete when student data is valid")
  @Test
  void returnCompleteWhenStudentDataIsValid() {

    given(service.createStudent(isA(Student.class)))
        .willReturn(Mono.empty());

    Mono<Void> response = controller.createStudent(student);

    StepVerifier.create(response)
        .verifyComplete();
  }

  @DisplayName("Return list of students when status is active")
  @Test
  void returnListOfStudentsWhenStatusIsActive() {

    given(service.listStudentByStatus(anyString()))
        .willReturn(Flux.just(student));

    Flux<Student> response = controller.listStudentByStatus();

    StepVerifier.create(response)
        .expectNext(student)
        .verifyComplete();
  }

  @ParameterizedTest(name = "{0}")
  @CsvSource(value = {"is empty|''", "is null|"}, delimiter = '|')
  @DisplayName("Return error when student data")
  void returnErrorWhenStudentData(String details, String param) throws Exception {
    mockMvc = MockMvcBuilders.standaloneSetup(controller)
        .setControllerAdvice(new ApiExceptionHandler())
        .build();
    objectMapper = new ObjectMapper();
    student.setId(param);

    mockMvc.perform(post("/api/students")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(student)))
        .andExpect(status().isBadRequest())
        .andExpect(result -> assertTrue(
            result.getResolvedException() instanceof MethodArgumentNotValidException));
  }


}
