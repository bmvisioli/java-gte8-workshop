package functions;


import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

class Person {

  private String name;

  public Person() { this.name = "Jonh Doe"; }
  public Person(String name) { this.name = name; }

  public String sayMyName() { return name; }

  public void tellMyName(String first, String last) { this.name = first + " " + last; }

  public static Person theOneWhoKnocks() { return new Person("Walter White"); }

}

public class Explanation03 {

  public static void main(String args[]) {

    Supplier<Person> supplierPerson = () -> new Person();
    Supplier<Person> supplierPersonMR = Person::new;

    Function<String, Person> functionPerson = (string) -> new Person(string);
    Function<String, Person> functionPersonMR = Person::new;

    Person person = supplierPersonMR.get();
    BiConsumer<String, String> biConsumer = (first, last) -> person.tellMyName(first, last);
    BiConsumer<String, String> biConsumerMR = person::tellMyName;
    biConsumerMR.accept("Saul", "Goodman");

    //Method Reference on class methods
    nameTeller(Person::new);
    nameTeller(Person::theOneWhoKnocks);

    //Method Reference on instance methods
    namePrinter(person::sayMyName);
    nameAssigner(person::tellMyName);

  }

  public static void nameTeller(Supplier<Person> personSupplier) {
    System.out.println(personSupplier.get().sayMyName());
  }

  public static void namePrinter(Supplier<String> nameSupplier) {
    System.out.println(nameSupplier.get());
  }


  public static void nameAssigner(BiConsumer<String, String> biConsumer) {
    biConsumer.accept("Jesse", "Pinkman");
  }

}


