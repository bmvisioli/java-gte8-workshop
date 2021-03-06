package misc;

import static boilerplate.Boilerplate.println;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public interface Interfaces {

  /*
       ____           __                   ____
      /  _/   ____   / /_  ___    _____   / __/  ____ _  _____  ___    _____
      / /    / __ \ / __/ / _ \  / ___/  / /_   / __ `/ / ___/ / _ \  / ___/
    _/ /    / / / // /_  /  __/ / /     / __/  / /_/ / / /__  /  __/ (__  )
   /___/   /_/ /_/ \__/  \___/ /_/     /_/     \__,_/  \___/  \___/ /____/
  */

  String A_CONSTANT = "I'm available since Java 7 but you knew that already";

  // Since Java 8 interfaces can have concrete methods with keyword default
  default void aConcreteMethod() {
    println("I wasn't even possible before!");
    aPrivateMethod();
  }

  // And static methods were introduced as well
  static void aStaticMethod() {
    println("Utility functions anyone? package objects?");
    aPrivateStaticMethod();
  }

  // Java 9 then introduced private methods
  private void aPrivateMethod() {
    println("Private methods in a interface?");
  }

  // And PRIVATE STATIC methods, how cool is that?
  private static void aPrivateStaticMethod() {
    println("Reasonably cool.");
  }

  class Main {

    public static void main(String[] args){

      println("Have I mentioned inner types in interfaces? Since 1.7");

      println(Interfaces.A_CONSTANT);

      Interfaces interfaces = new Interfaces() {};

      interfaces.aConcreteMethod();
      Interfaces.aStaticMethod();

      // Real life examples - Expansion of already widely used interfaces

      List<Integer> list = new ArrayList<>(List.of(1,2,3)); // List.of is a static method
      Comparator comparator = Comparator.naturalOrder().reversed(); // Same here
      list.sort(comparator); // List.sort is a default method of List

      // And you can use the above instead of
      Collections.sort(list, comparator);

      println(list);

    }
  }
}