package test.java.monads;

import static boilerplate.Boilerplate.delayedResult;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FutureExercise {

  /** Simple implementation of a StopWatch to be used in the tests */
  class StopWatch {
    private Instant begin = Instant.now();
    public Duration soFar() { return Duration.between(begin, Instant.now()); }
  }

  @Test
  @DisplayName("CFutures run in parallel")
  public void parallelFutures() {
    var watch = new StopWatch();
    var initial = List.of(
        delayedResult(2, "C"),
        delayedResult(2, "A"),
        delayedResult(2, "F"),
        delayedResult(2, "E")
    );

    var actualTime = watch.soFar().getSeconds();
    var actualString = String.join("", initial);

    // Time spent is less than 3 seconds
    var expectedTime = 8;
    var expectedString = "CAFE";

    assertEquals(expectedTime, actualTime);
    assertEquals(expectedString, actualString);
  }

}
