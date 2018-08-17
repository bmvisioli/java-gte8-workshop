package collections;

import java.util.ArrayList;
import java.util.List;

public class Explanation01 {

  public static void main(String args[]) {

    // Instantiating collections with initial values before Java 8 - notice the double {{ }}
    // Inner-class with init code block
    List<String> oldSchool = new ArrayList<>() {{
      add("Don't");
      add("do");
      add("{{ }}");
      add("anymore");
    }};

    System.out.println(String.join(" ", oldSchool));

    // Instantiating collections with initial values after Java 8 - Factory Methods!
    List<String> millennialsWay = List.of("Use", "factory", "methods", "instead");
    // Also Map.of() and Set.of()...

    System.out.println(String.join(" ", millennialsWay));

    try{
      millennialsWay.add("And then can I add some more entries?");
    } catch (UnsupportedOperationException ex) {
      System.out.println("Nope, you can't add elements, the collections created like this are immutable.");
      // And are all defined in ImmutableCollections
    }

    //BTW: Iterable's new forEach method.
    List.of("I've never been iterated like this").forEach(System.out::println);
  }

}
