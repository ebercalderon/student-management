package com.scotiabank.studentmanagement.model.entity;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
  @NotBlank(message = "El ID del estudiante no puede ser vació")
  @NotNull(message = "El ID del estudiante no puede ser nulo")
  private String id;

  @NotBlank(message = "El nombre del estudiante no puede ser vació")
  @NotNull(message = "El nombre del estudiante no puede ser nulo")
  private String name;

  @NotBlank(message = "El apellido del estudiante no puede ser vació")
  @NotNull(message = "El apellido del estudiante no puede ser nulo")
  private String lastName;

  @NotBlank(message = "El estado del estudiante no puede ser vació")
  @NotNull(message = "El estado del estudiante no puede ser nulo")
  private String status;

  @NotNull(message = "La edad del estudiante no puede ser nula")
  @Min(value = 1, message = "La edad del estudiante debe ser al menos 1")
  @Max(value = 100, message = "La edad del estudiante debe ser como máximo 100")
  private Integer age;

}
