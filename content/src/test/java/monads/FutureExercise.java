package monads;

import static boilerplate.Boilerplate.__;
import static boilerplate.Boilerplate.delayedResult;
import static boilerplate.Boilerplate.futureResult;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FutureExercise {

  /** Simple implementation of a StopWatch to be used in the tests */
  class StopWatch {
    private Instant begin = Instant.now();
    public Duration soFar() { return Duration.between(begin, Instant.now()); }
  }

  @Test
  @DisplayName("CFutures in parallel")
  public void parallelFutures() {
    var watch = new StopWatch();

    // TODO modify the following block to fix the tests
    var actual = List.of(
      delayedResult(2, "C"),
      delayedResult(2, "A"),
      delayedResult(2, "F"),
      delayedResult(2, "E")
    );

    var actualTime = watch.soFar().getSeconds();
    var actualString = String.join("", actual);

    // Time spent is less than 3 seconds
    var expectedTime = 2;
    var expectedString = "CAFE";

    assertEquals(expectedTime, actualTime);
    assertEquals(expectedString, actualString);
  }

  private static CompletableFuture<Integer> eventuallyAddOne(Integer c) {
    return futureResult(0.5, c + 1);
  }

  private static CompletableFuture<String> eventuallyToString(Object c) {
    return futureResult(0.5, c.toString());
  }

  @Test
  @DisplayName("CFutures transformations")
  public void futuresTransformations() {

    var initial = futureResult(1, 1);

    var expected = Character.valueOf('2');

    // Use eventuallyAddOne and eventuallyToString methods
    var actual = __;

    assertEquals(expected, actual);

  }

  @Test
  @DisplayName("CFutures first come first served")
  public void futuresFirstCome() {

    var initial = List.of(
        futureResult(0.5, "Ik"),
        futureResult(0.4, "Jij"),
        futureResult(0.3, "Hij"),
        futureResult(0.2, "Zij")
    );

    var expected = "Zij";

    var actual = __;

    assertEquals(expected, actual);

  }





}
