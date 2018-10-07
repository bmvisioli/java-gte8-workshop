package misc;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class Var {

  public static void main(String[] args) {

    // Java 7 introduced the chars saver diamond operator for generics type inference

    List<String> stringList = new ArrayList<String>();
    List<String> diamondList = new ArrayList<>();

    // But I bet you're a bit tired of declaring the type of every single variable

    String duh = "Yes, I'm a String";

    // Well, worry not my friend, Java 10 brought to us the var keyword!

    var obvious = false;                            // For primitives
    var evident = new Object();                     // And objects
    var crystalClear = new ArrayList<String>();     // And generics
    var dubious = new ArrayList<>();                // And diamonds

    // Really good for
    try(var fis = new FileInputStream("/path")) {} catch (Exception ignored) {}
    for(var s : List.of("string","string","string","string")) {}

    // That's great, just be aware of some assumptions that are done

    // A number is always cast to int or double
    var intNumber = 1;
    var doubleNumber = 1.0;
    var hexIntNumber = 0xF;
    var scienceNumber = 1e10;

    // You can force longs and floats with suffixes
    var longNumberVar = 1L;
    var floatNumber= 1.0F;

    // But for the other types no luck, stick to declare the type
    short shortNumber = 1;
    byte veryShortNumber = 1;
    char unsignedShortNumber = 1;

    // Unfortunately it's wasn't this time we saw val

    final var val = "Well, I guess it's a beginning";

    /*
     And is available only for local type variable so the following don't compile:

     Fields:

      class Var { var field; }

     Method parameters:

      class Var { void method(var param) {} }

     Local types but without initialization:

      var counter;

     Or initializing with null:

      var nullable = null;

     With lambdas

      var func = () -> {};

     */
  }

}
