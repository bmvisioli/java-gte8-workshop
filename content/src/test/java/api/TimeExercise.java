package api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static boilerplate.Boilerplate.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TimeExercise {

  @Test
  @DisplayName("From time to date+time+timezone")
  public void testTransformation() {

    var initial = LocalTime.of(10,15,30);
    var expected = ZonedDateTime.parse("2007-12-03T10:15:30+02:00");

    var actual = __;

    assertEquals(expected, actual);
  }

  @Test
  @DisplayName("Previous Thursday")
  public void test() {

    var initial = LocalDate.of(2018, 10, 11);
    var expected = LocalDate.of(2018, 10, 4);

    // How do we add or subtract days?
    var actual = __;

    assertEquals(expected, actual);
  }

}
