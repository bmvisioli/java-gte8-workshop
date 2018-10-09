package boilerplate;

public class Boilerplate {

  /** Returns the result after seconds */
  public static <E> E delayedResult(long seconds, E result) {
    try {
      Thread.sleep(seconds * 1000);
    } catch (InterruptedException ignored) { }
    return result;
  }

  /** Alias for System.out.println */
  public static void println(Object obj) {
    System.out.println(obj);
  }

}
