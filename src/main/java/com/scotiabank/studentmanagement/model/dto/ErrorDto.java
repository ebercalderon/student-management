package com.scotiabank.studentmanagement.model.dto;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * <b>Class</b>: ErrorDto.<br/>
 */
@Getter
@Setter
@AllArgsConstructor

public class ErrorDto {

  private int status;
  private String message;
  private Map<String, String> details;
  private String time;

}
