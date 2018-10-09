package collections;

import static boilerplate.Boilerplate.delayedResult;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Exercise {

  @Test
  @DisplayName("Predicates with odd numbers")
  void testPredicates() {
    var initial = List.of(9,27,29,21,0);

    // Check list contains only odd numbers
    var expected0 = false;
    var actual0 = true;
    assertEquals(expected0, actual0);

    // Check list contains at least one even number
    var expected1 = true;
    var actual1 = false;
    assertEquals(expected1, actual1);

    // Find first prime number
    var expected2 = Optional.of(29);
    var actual2 = Optional.empty();
    assertEquals(expected2, actual2);
  }

  @Test
  @DisplayName("Stream Group By Initial")
  void testGroupBy() {

    var initial = List.of(
        "Dominican Republic", "Democratic Republic of the Congo",
        "Argentina", "Algeria",
        "Colombia", "Cameroon",
        "Brazil", "Botswana");

    var expected = Map.of(
        'A', List.of("AR", "AL"),
        'B', List.of("BR", "BO"),
        'C', List.of("CO", "CA"),
        'D', List.of("DO", "DE"));

    var actual = initial;

    assertEquals(expected, actual);
  }

  @Test
  @DisplayName("Stream inverting String")
  void testReverseString() {

    var initial = List.of("nevEr", "Odd", "Or", "eveN");
    // This is a Palindrome if you haven't noticed
    var expected = "NeverOddOrEven";

    // Tip: Don't use array of chars
    var actual = initial.stream()
        .map(s -> s.split(""))
        .flatMap(Arrays::stream)
        .reduce("", (c,r) -> r+c);
    assertEquals(expected, actual);

  }

  // The following class will be used in the next two exercises
  class Database {
    String preffix = "Cached";

    StringBuffer query(Integer id) {
      return doQuery(id);
    }

    /** A very costly operation */
    private StringBuffer doQuery(Integer id) {
      return delayedResult(1, new StringBuffer(preffix + id));
    }

    int getPoolSize() { return 0; }
  }

  @Test
  @DisplayName("Database cached result")
  void cache() {

    /*
      TODO
      Update Database class so that it caches calls
      in a way that for the same parameter X the result is
      always the same object Y
    */

    var db = new Database();

    var initial = List.of(1,2,3);

    var expected = List.of(
        db.query(1),
        db.query(2),
        db.query(3)
    );
    var expectedPoolSize = 3;

    var actual = initial; // TODO Transform the elements to StringBuffer using the "pool"
    var actualPoolSize = db.getPoolSize();

    // Stream "Zip" and then forEachPair assert== (for-by-index would work too but hey: streams 🤘)
    IntStream.range(0, actual.size())
        .boxed()
        .collect(Collectors.toMap(expected::get, actual::get))
        .forEach(Assertions::assertSame);

    assertEquals(expectedPoolSize, actualPoolSize);
  }

  @Test
  @DisplayName("Database cache update")
  void cacheUpdate() {

    /*
      TODO
      Update Database class so a cache entry
      can be update.
    */

    var pool = new Database();

    var initial = "";

    var actual = initial; // TODO Transform the elements to StringBuffer using the "pool"
    var actualPoolSize = pool.getPoolSize();

    // Stream "Zip" and then forEachPair assert== (for-by-index would work too but hey: streams 🤘)


  }



}