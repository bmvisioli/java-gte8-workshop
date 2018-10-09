package functions;

import static boilerplate.Boilerplate.println;

import java.util.concurrent.Callable;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;
import java.util.function.UnaryOperator;

public class Functions {

  public static void main(String[] args) {

    /*
        ______                          __     _
       / ____/  __  __   ____   _____  / /_   (_)  ____    ____    _____
      / /_     / / / /  / __ \ / ___/ / __/  / /  / __ \  / __ \  / ___/
     / __/    / /_/ /  / / / // /__  / /_   / /  / /_/ / / / / / (__  )
    /_/       \__,_/  /_/ /_/ \___/  \__/  /_/   \____/ /_/ /_/ /____/

    */

    // Passing behaviour as parameter before Java 8 -> Anonymous Class
    new Thread(
        new Runnable() {
          @Override
          public void run() {
            println("I just felt like running (as an inner class)!");
          }
        }
    ).start();

    // Passing behaviour as parameter after Java 8 -> Lambda
    new Thread(
        () -> println("I just felt like running (as a lambda)!")
    ).start();

    // No parameters, no return, pure side effects;
    Runnable runnable = () -> println("Runnable");
    runnable.run();

    // No parameters, returns a T instance;
    Supplier<String> supplier = () -> "Supplier";
    println(supplier.get());

    // Same but throws a checked exception;
    Callable<String> callable = () -> "Callable";
    try{ println(callable.call()); } catch (Exception ignored) {}

    // One T parameter, no return, pure side effects;
    Consumer<String> consumer = (string) -> println(string);

    // One T parameter, returns a V instance;
    Function<String, String> function = (string) -> "Function with " + string;
    println(function.apply("string"));

    // Two-arity versions
    BiConsumer<String, String> biConsumer;
    BiFunction<String, String, String> biFunction;

    // Specialized versions;
    Predicate<String> predicate = (string) -> string.isEmpty();
    predicate.test("predicate");

    ToIntFunction<String> toIntFunction = (string) -> string.length();
    toIntFunction.applyAsInt("toIntFunction");

    UnaryOperator<String> unaryOperator = (string) -> string;
    unaryOperator.apply("unaryOperator");

    // What about the utterly necessary twenty-two-arity function?
    TwentyTwoParametersFunction<
        String, String, String, String,
        String, String, String, String,
        String, String, String, String,
        String, String, String, String,
        String, String, String, String,
        String, String, String>
        longAssFunction = (a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v)
        -> "22 parameters? What was I trying to do again?";

    println(longAssFunction.dearLord("a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v"));

    // Usage in a method
    printResult(() -> "I'm being supplied");
  }

  public static void printResult(Supplier<String> supplier) {
    println(supplier.get());
  }
}


@FunctionalInterface
interface TwentyTwoParametersFunction<A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, X> {
  X dearLord(A a, B b, C c, D d, E e, F f, G g, H h, I i, J j, K k, L l, M m, N n, O o, P p, Q q, R r, S s, T t, U u, V v);
}