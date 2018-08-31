package monads;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CompletableFutureExplanation {

  public static void main(String args[]) throws ExecutionException, InterruptedException {

    Callable<Integer> backToTheFuture = () -> { try{ Thread.sleep(1000); } finally { return 42; } };

    // Before Java 8 we had to deal with the inconvenient Future/FutureTask classes
    ExecutorService exec = Executors.newSingleThreadExecutor();

    Future<Integer> futureInt = exec.submit(backToTheFuture);

    while(!futureInt.isDone()) { System.out.println("Waiting, go get a biscuit."); }
    System.out.println("The future arrived and it is: " + futureInt.get()); // For fuck's sake, get throws a CHECKED exception!

    // Java 8 introduced the much friendlier CompletableFuture
    Com

  }

}
