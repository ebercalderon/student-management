package com.scotiabank.studentmanagement.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <b>Class</b>: ErrorCatalog.<br/>
 */
@Getter
@AllArgsConstructor
public enum ErrorCatalog {

  E001(400, "Error de validación"),
  E002(409, "El ID del alumno ya existe");

  private final int status;
  private final String description;
}
