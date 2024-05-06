package com.scotiabank.studentmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

/**
 * Clase que representa a un estudiante.<br/>
 * <b>Class</b>: Student<br/>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {

  @Id
  private String id;
  private String name;
  private String lastName;
  private String status;
  private Integer age;

}
