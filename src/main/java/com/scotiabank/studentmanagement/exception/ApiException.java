package com.scotiabank.studentmanagement.exception;

import com.scotiabank.studentmanagement.model.enums.ErrorCatalog;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * <b>Class</b>: ApiException.<br/>
 */
public class ApiException extends ResponseStatusException {

  public ApiException(ErrorCatalog e) {
    super(HttpStatus.valueOf(e.getStatus()), e.getDescription());
  }

}
