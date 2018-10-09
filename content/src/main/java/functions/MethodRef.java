package functions;


import static boilerplate.Boilerplate.println;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class MethodRef {

  public static void main(String[] args) {

    /*
        __  ___         __     __                __    ____           ____
       /  |/  /  ___   / /_   / /_   ____   ____/ /   / __ \  ___    / __/
      / /|_/ /  / _ \ / __/  / __ \ / __ \ / __  /   / /_/ / / _ \  / /_
     / /  / /  /  __// /_   / / / // /_/ // /_/ /   / _, _/ /  __/ / __/
    /_/  /_/   \___/ \__/  /_/ /_/ \____/ \__,_/   /_/ |_|  \___/ /_/


      While lambdas are already a great way to save boilerplate code
      a new syntactic sugar was introduced along: Method Reference
      i.e.:
        Class::method
        Object::method
     */

    // 0 - Arity
    Supplier<Person> supplierPerson = () -> new Person();
    Supplier<Person> supplierPersonMR = Person::new;

    // 1 - Arity
    Function<String, Person> functionPerson = (string) -> new Person(string);
    Function<String, Person> functionPersonMR = Person::new;
    functionPerson.apply("Dennis Ritchie");

    // 2 - Arity
    Person person = supplierPersonMR.get();
    BiConsumer<String, String> biConsumer = (first, last) -> person.tellMyName(first, last);
    BiConsumer<String, String> biConsumerMR = person::tellMyName;
    biConsumerMR.accept("Edsger", "Dijkstra");

    // Method Reference on class methods
    nameTeller(Person::new);
    nameTeller(Person::firstOfHisName);

    // Method Reference on instance methods
    namePrinter(person::sayMyName);
    nameAssigner(person::tellMyName);

    // One small consideration of Lambda vs Method Reference
    try {
      String string = null;
      Runnable run1 = () -> string.length();
      Runnable run2 = string::length;
    } catch (NullPointerException npe) { // Catching this NPE for the exercise, don't do this on the wild
      println("NPE at " + npe.getStackTrace()[1]);
    }
  }

  public static void nameTeller(Supplier<Person> personSupplier) {
    println(personSupplier.get().sayMyName());
  }

  public static void namePrinter(Supplier<String> nameSupplier) {
    println(nameSupplier.get());
  }

  public static void nameAssigner(BiConsumer<String, String> biConsumer) {
    biConsumer.accept("James", "Gosling");
  }

}

class Person {

  private String name;

  public Person() { this.name = "John Doe"; }
  public Person(String name) { this.name = name; }

  public String sayMyName() { return name; }

  public void tellMyName(String first, String last) { this.name = first + " " + last; }

  public static Person firstOfHisName() { return new Person("Alan Turing"); }

}


