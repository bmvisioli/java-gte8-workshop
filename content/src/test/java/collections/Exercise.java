package collections;

import static boilerplate.Boilerplate.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;
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
    var actual0 = __;
    assertEquals(expected0, actual0);

    // Check list contains at least one even number
    var expected1 = true;
    var actual1 = __;
    assertEquals(expected1, actual1);

    // Find first prime number
    var expected2 = Optional.of(29);
    var actual2 = __;
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

    var actual = __;

    assertEquals(expected, actual);
  }

  @Test
  @DisplayName("Stream inverting String")
  void testReverseString() {

    var initial = List.of("nevEr", "Odd", "Or", "eveN");
    // This is a Palindrome if you haven't noticed
    var expected = "NeverOddOrEven";

    // Tip: Don't use array of chars
    var actual = __;
    assertEquals(expected, actual);

  }

  // The following class will be used in the next two exercises
  class DatabaseAccess {
    String prefix = "Cached";

    StringBuffer queryCached(Integer id) {
      return internalDoQuery(id);
    }

    StringBuffer queryFresh(Integer id, String newPrefix) {
      this.prefix = newPrefix;
      return internalDoQuery(id);
    }

    int getCacheSize() {
      return 0;
    }

    /** A "very" costly operation that we want to avoid as much as possible */
    private StringBuffer internalDoQuery(Integer id) { return delayedResult(1, new StringBuffer(prefix + id)); }

  }

  @Test
  @DisplayName("DatabaseAccess cached result")
  void cache() {

    /*
      TODO
      Update DatabaseAccess class so that queryCached returns
      in a way that for the same parameter X the result is
      always the same object Y
    */

    var db = new DatabaseAccess();

    var initial = List.of(1,2,3);

    var expected = List.of(
        db.queryCached(1),
        db.queryCached(2),
        db.queryCached(3)
    );
    var expectedCacheSize = 3;

    var actual = (List<StringBuffer>) __; // TODO Transform the elements to StringBuffer using the "db"
    var actualPoolSize = db.getCacheSize();

    matchAllElements(expected, actual, Assertions::assertSame);

    assertEquals(expectedCacheSize, actualPoolSize);
  }

  @Test
  @DisplayName("DatabaseAccess cache update")
  void cacheUpdate() {

    /*
      TODO
      Update DatabaseAccess class so that queryFresh
      updates a cache entry and returns it.
    */

    var db = new DatabaseAccess();

    var actual = List.of(
        db.queryCached(1),
        db.queryCached(1),
        db.queryFresh(1, "Refreshed"),
        db.queryCached(1)

    );

    var expected = List.of(
        // 0-1 should be same instance
        actual.get(0),
        actual.get(0),
        // 2-3 Should be same instance
        actual.get(2),
        actual.get(2)
    );
    var expectedCacheSize = 1;

    var actualCacheSize = db.getCacheSize();

    matchAllElements(expected, actual, Assertions::assertSame);

    assertEquals(expectedCacheSize, actualCacheSize);

  }

  private <E> void matchAllElements(List<E> expected, List<E> actual, BiConsumer<E,E> assertion) {
    assertEquals(expected.size(), actual.size());
    // for-by-index would also work but hey: streams 🤘
    IntStream.range(0, actual.size()).boxed()
        .forEach(i -> assertion.accept(expected.get(i), actual.get(i)));
  }


}