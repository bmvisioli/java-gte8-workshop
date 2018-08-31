package monads;

import java.util.Optional;

public class OptionalExplanation {

  public static void main(String args[]) throws Throwable {

    // I guess we all have done a lot of the following in our java code (< java 8)
    Integer value = oldNullableResult();
    if(value != null) {
      // We would then process value
      value++;
    } else {
      // But the value is null, obviously, so we set a default
      value = 0;
    }

    // Java 8 introduced Optional to deal with values that may or may not be present
    Optional<Integer> optInteger = newOptionalResult();
    optInteger.map(a -> a + 1).orElse(0);

    // We can even encapsulate nullable results into Optionals
    Optional.ofNullable(oldNullableResult()).map(a -> a + 1).orElse(0);

    // In your method you can create Optionals as follow
    Optional<Integer> optOne = Optional.of(1);
    Optional<Integer> optEmpty = Optional.empty();

    // In most cases you'll want to use one of the following to access the value (from preferred to utter evil)
    Integer valueInt;

    valueInt = optOne.orElse(0); // Returns the value or the default
    valueInt = optOne.orElseGet(() -> 0); // Returns the value or the default after evaluation of function (for expensive computation);
    valueInt = optOne.orElseThrow(); // Returns the value or throws NoSuchElementException
    valueInt = optOne.orElseThrow(IllegalArgumentException::new); // You can guess, but beware that this exception is Checked!
    valueInt = optOne.isPresent() ? optEmpty.get() : 0; // Utter evil in most cases

    // You can also perform some side-effects depending on the presence of the value
    optOne.ifPresent(System.out::println);
    optOne.ifPresentOrElse(System.out::println, () -> System.out.println("Default"));

    // As you saw before we can transform the inner value, if present
    optInteger.map(integer -> integer * 2);

    // And filter
    optOne.filter(op1 -> op1 % 2 == 0); // Returns Optional.empty

    // And combine with other Optionals
    Optional<Integer> optTwo = Optional.of(2);
    optOne.or(() -> optTwo).orElse(0); // Returns optOne.value, or optTwo.value or the default 0
    optOne.flatMap(op1 -> optTwo.map(op2 -> op1 + op2)); // Return the 1 + 2 if both are present

    // Bonus: Transform your Optional to a Stream of one or zero values
    optOne.stream();

  }

  private static Integer oldNullableResult() {
    return null;
  }

  private static Optional<Integer> newOptionalResult() {
    return Optional.empty();
  }

}
