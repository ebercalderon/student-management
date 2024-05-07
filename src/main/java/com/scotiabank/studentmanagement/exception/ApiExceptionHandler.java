package com.scotiabank.studentmanagement.exception;

import com.scotiabank.studentmanagement.model.dto.ErrorDto;
import com.scotiabank.studentmanagement.model.enums.ErrorCatalog;
import com.scotiabank.studentmanagement.util.Utils;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;

/**
 * <b>Class</b>: ApiExceptionHandler.<br/>
 */
@ControllerAdvice
public class ApiExceptionHandler {

  /**
   * apiException.
   */
  @ExceptionHandler(ApiException.class)
  public ResponseEntity<ErrorDto> apiException(ApiException ex) {
    return buildResponse(ex.getStatus().value(), ex.getReason(), new HashMap<>());
  }

  /**
   * validationException.
   */
  @ExceptionHandler(WebExchangeBindException.class)
  public ResponseEntity<ErrorDto> validationException(WebExchangeBindException ex) {
    Map<String, String> details = new HashMap<>();
    ex.getBindingResult().getFieldErrors()
        .forEach(error -> details.put(error.getField(), error.getDefaultMessage()));
    return buildResponse(ErrorCatalog.E001.getStatus(), ErrorCatalog.E001.getDescription(),
        details);
  }

  private ResponseEntity<ErrorDto> buildResponse(int status, String message,
      Map<String, String> details) {
    String time = Utils.getDateFormatted();
    ErrorDto errorDto = new ErrorDto(status, message, details, time);
    return new ResponseEntity<>(errorDto, HttpStatus.valueOf(status));
  }

}
