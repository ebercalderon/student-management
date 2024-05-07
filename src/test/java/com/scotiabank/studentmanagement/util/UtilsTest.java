package com.scotiabank.studentmanagement.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.scotiabank.studentmanagement.util.constants.Constants;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


class UtilsTest {

  @ParameterizedTest(name = "{0}")
  @CsvSource(value = {"12345678|****5678", "75429123|****9123"}, delimiter = '|')
  @DisplayName("Return obfuscated information when it is sensitive")
  void ReturnObfuscatedInformationWhenItIsSensitive(String input, String expected) {
    String result = Utils.obfuscateInfo(input);
    assertEquals(expected, result);
  }

  @DisplayName("Return formatted string when get date")
  @Test
  void returnFormattedStringWhenGetDate() {
    SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
    String expected = dateFormat.format(new Date());
    String result = Utils.getDateFormatted();
    assertEquals(expected, result);
  }

}
