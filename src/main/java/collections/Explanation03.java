package collections;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Explanation03 {

  public static void main(String args[]) {

    // Steams offer a way to process collections in a, well, streaming fashion
    // We can create them using factory methods
    Stream<String> factoryStream = Stream.of("The factory put me here");

    // Using the builder pattern
    Stream<String> builderStream = Stream.<String>builder().add("The builder put me in here").build();

    // But most commonly we'll have a collection and transform it to a Stream
    Stream<String> collectionStream = List.of("I was a collection at first").stream();

    // Can be created parallel too
    List.of("I was a collection at first").parallelStream();

    // Or become parallel later
    Stream.empty().parallel();

    // Streams offer lots of high-order functions for operations performed on collections

    // Map each element to a new element
    collectionStream.map(str -> str + " but now I'm a stream");

    // Filter elements depending on a predicate
    IntStream.range(1, 10).filter(i -> i % 2 == 0);

    // Sort it
    Stream.of(5, 4, 3, 2, 1).sorted();
    Stream.of(3, 1, 5, 2, 4).sorted(Comparator.<Integer>naturalOrder().reversed());

    // Eliminate duplicates
    Stream.of(1, 1, 2, 2, 3, 3).distinct();

    // But Streams are lazily-evaluated and all of above are intermediary steps
    // So something like this prints nothing!
    Stream<String> peekStream = Stream.of("Finally evaluated").peek(System.out::println);
    System.out.println("Stream has been created but not evaluated");

    // We need a termination operation to actually evaluate the Stream
    peekStream.collect(Collectors.toList()); // Materializes the stream into a list

    Stream.of(1, 2, 3).count(); // returns 3
    Stream.empty().forEach(System.out::println); // Produces a side-effect
    Stream.of("a", "b", "cd").allMatch(str -> str.length() == 1); // returns false
    // ...

    // But never, I said never, try to re-use a stream that is already terminated
    Stream initStream = Stream.empty();
    initStream.findFirst(); // Termination operation

    try {
      // Otherwise...
      initStream.findAny();
    } catch(IllegalStateException e) {
      System.out.println(e.getMessage()); // "stream has already been operated upon or closed"
    }

  }

}
