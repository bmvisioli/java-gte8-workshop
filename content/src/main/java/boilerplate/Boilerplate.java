package boilerplate;

import java.util.concurrent.CompletableFuture;

public class Boilerplate {

  /** Returns the result after seconds */
  public static <E> E delayedResult(double seconds, E result) {
    try {
      Thread.sleep(Math.round(seconds * 1000));
    } catch (InterruptedException ignored) { }
    return result;
  }

  public static <E> CompletableFuture<E> futureResult(double seconds, E result) {
    return CompletableFuture.supplyAsync(() -> delayedResult(seconds, result));
  }

  /** Alias for System.out.println */
  public static void println(Object obj) {
    System.out.println(obj);
  }

  /** Placeholder for tests */
  public static final Object __ = null;

}
