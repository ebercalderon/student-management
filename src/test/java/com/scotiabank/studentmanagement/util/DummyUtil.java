package com.scotiabank.studentmanagement.util;

import com.scotiabank.studentmanagement.model.entity.Student;

/**
 * Utilitario de objetos dummy para los test.<br/>
 * <b>Class</b>: DummyUtil<br/>
 */
public class DummyUtil {

  public static Student getStudent() {
    Student student = new Student();
    student.setId("75123456");
    student.setName("Eber");
    student.setLastName("Calderon");
    student.setStatus("ACTIVO");
    student.setAge(24);
    return student;
  }

}
