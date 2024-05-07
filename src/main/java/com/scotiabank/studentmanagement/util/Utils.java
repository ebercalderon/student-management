package com.scotiabank.studentmanagement.util;

import com.scotiabank.studentmanagement.util.constants.Constants;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Utilitario.<br/>
 * <b>Class</b>: Utils<br/>
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Utils {

  public static String getDateFormatted() {
    SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
    return dateFormat.format(new Date());
  }

  public static String obfuscateInfo(String id) {
    return id.replaceAll(Constants.OBFUSCATE_FORMAT, "*");
  }

}
