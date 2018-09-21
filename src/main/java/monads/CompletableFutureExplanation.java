package monads;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CompletableFutureExplanation {

  public static void main(String args[])
      throws ExecutionException, InterruptedException, TimeoutException {

    Callable<Integer> eventualResult = () -> delayedResult(1, 42);

    // Before Java 8 we had to deal with the inconvenient Future/FutureTask classes
    ExecutorService exec = Executors.newSingleThreadExecutor();

    Future<Integer> futureInt = exec.submit(eventualResult);

    while (!futureInt.isDone()) {
      System.out.println("Waiting, go get a biscuit.");
    } // This line is redundant as .get() blocks the thread;
    System.out.println("The future arrived and it is: " + futureInt
        .get()); // For fuck's sake, .get() throws a CHECKED exception!

    // But that Future is in the past (wink) as Java 8 introduced the much friendlier CompletableFuture
    Supplier<Integer> eventualResult2 = () -> delayedResult(1, 42);
    CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(eventualResult2);

    // Obtaining the value non-blocking way
    completableFuture.whenComplete((result, exception) -> System.out.println("A new future arrived and it is: " + result));

    // Or blocking the whole thing
    completableFuture.get(); // Block until it completes (successfully or not) or the apocalypse happens
    completableFuture.get(1, TimeUnit.SECONDS); // Blocks for at most this amount of time and then throws Exception
    completableFuture.getNow(0); // Gets the value if already available or return a default

    // And CompletableFutures have tons of goodies, like future chaining
    completableFuture
        .thenRun(() -> System.out.println("What's beyond the Future?")) // chaining with blocking code
        .thenRunAsync(() -> System.out.println("If you need me I'll be in another thread;")) // runs in...
        .exceptionally(e -> {
          e.printStackTrace();
          return null;
        }); // threats exceptions

    // And map/flatMap high order functions (with terrible names tho)
    CompletableFuture.supplyAsync(() -> "This is like a flat ... wait for it ...")
        .thenCompose(str -> CompletableFuture.supplyAsync(() -> str + " map")) // Combining two futures (flatMap)
        .thenApply(str -> str + "; and this is like a map") // Mapping the value inside the Future (map)
        .thenAccept(System.out::println); // Like thenRun but using the value of Future

    // Waiting for a list of independent Futures
    CompletableFuture<String> a = CompletableFuture.supplyAsync(() -> delayedResult(1, "a"));
    CompletableFuture<String> b = CompletableFuture.supplyAsync(() -> delayedResult(3, "b"));
    CompletableFuture<String> c = CompletableFuture.supplyAsync(() -> delayedResult(5, "c"));

    CompletableFuture.allOf(a, b, c).thenRun(() -> {
      // In this case you have to access the values by using the original Futures
      try {
        System.out.println("AllOf [" + a.get() + b.get() + c.get() + "]");
      } catch (Exception ignored) { }
    });

    // OR the below:  - btw join is similar to Thread.join which means it blocks the current thread!
    List<String> list = Stream.of(a, b, c).map(CompletableFuture::join).collect(Collectors.toList());
    System.out.println("Join [" + String.join("", list) + "]");


    // If your method returns a CompletableFuture you can create them as below
    CompletableFuture.completedFuture(42);
    CompletableFuture.failedFuture(new IllegalArgumentException("I didn't like yours arguments."));

    // And not only it's Completable but also is Cancelable
    completableFuture.cancel(true);

  }

  /** Utility method to avoid some boilerplate try-catches*/
  static <E> E delayedResult(long seconds, E result) {
    try {
      Thread.sleep(seconds * 1000);
    } catch (InterruptedException ignored) { }
    return result;
  }

}
