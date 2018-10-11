package monads;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static boilerplate.Boilerplate.*;

import java.util.Optional;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

public class OptionalExercise {

  @Test
  @DisplayName("Optional with Default")
  public void testOptionalDefault() {

    var initial = Stream.of("Not me").filter("You?"::equals).findFirst();
    var expected = Optional.of("Me then");

    var actual = __;

    assertEquals(expected, actual);

  }

  @Test
  @DisplayName("Optional with Throws")
  public void testOptionalException() {

    class MyException extends RuntimeException {}

    var initial = Stream.of("Not me").filter("You?"::equals).findFirst();
    var expected = MyException.class;

    Executable actual = () -> __.wait();

    assertThrows(expected, actual);

  }

  @Test
  @DisplayName("Optional alternatives")
  public void testOptionalCombine() {

    var initial0 = Stream.of("Not me").filter("You?"::equals).findFirst();
    var initial1 = Stream.of("Me then").filter("Me then"::equals).findFirst();

    var expected = Optional.of("Me then");

    // Check initial0 first and then initial1
    var actual = __;

    assertEquals(expected, actual);
  }

  @Test
  @DisplayName("Optional combine")
  public void testOptionalAppend() {

    var initial0 = Stream.of("You").filter("You"::equals).findFirst();
    var initial1 = Stream.of("Me").filter("Me"::equals).findAny();

    var expected = Optional.of("You and me");

    var actual = __;

    assertEquals(expected, actual);
  }

}
