package misc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public interface Interfaces {

  String A_CONSTANT = "I'm available since Java 7 but you knew that already";

  // Since Java 8 interfaces can have concrete methods with keyword default
  default void aConcreteMethod() {
    System.out.println("I wasn't even possible before!");
    aPrivateMethod();
  }

  // And static methods were introduced as well
  static void aStaticMethod() {
    System.out.println("Utility functions anyone? package objects?");
    aPrivateStaticMethod();
  }

  // Java 9 then introduced private methods
  private void aPrivateMethod() {
    System.out.println("Private methods in a interface?");
  }

  // And PRIVATE STATIC methods, how cool is that?
  private static void aPrivateStaticMethod() {
    System.out.println("Reasonably cool.");
  }

  class Main {

    public static void main(String args[]){

      System.out.println("Have I mentioned inner types in interfaces? Since 1.7");

      System.out.println(Interfaces.A_CONSTANT);

      Interfaces interfaces = new Interfaces() {};

      interfaces.aConcreteMethod();
      Interfaces.aStaticMethod();

      // Real life examples - Expansion of already widely used interfaces

      List<Integer> list = new ArrayList<>(List.of(1,2,3)); // List.of is a static method
      Comparator comparator = Comparator.naturalOrder().reversed(); // Same here
      list.sort(comparator); // List.sort is a default method of List

      // And you can use the above instead of
      Collections.sort(list, comparator);

      System.out.println(list);

    }
  }
}