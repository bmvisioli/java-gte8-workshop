package consumer;

import static boilerplate.Boilerplate.println;

import java.util.ServiceLoader;
import modules.exposed.Exposed;
import modules.exposed.ExposedInterface;

public class Consumer {

  public static void main(String[] args) {

    println(Exposed.exposed());
    // println("trying to access " + Hidden.hidden());

    callHiddenByReflection();
    callOnlyByReflection();
    callServiceImplementation();

  }

  /** Tries to call a method that is not "exported" or "opened" */
  private static void callHiddenByReflection() {
    try {
      var hiddenClass = Class.forName("modules.hidden.Hidden");
      var hiddenMethod = hiddenClass.getMethod("hidden");
      var hiddenResult = hiddenMethod.invoke(hiddenClass.getDeclaredConstructor().newInstance());

      println("Calling Hidden by reflection results in " + hiddenResult);
    } catch(Exception e) {
      System.err.println(e.getMessage());
    }
  }

  private static void callOnlyByReflection() {
    try {
      var onlyReflectionClass = Class.forName("modules.reflection.OnlyByReflection");
      var onlyReflectionMethod = onlyReflectionClass.getMethod("reflectivelyAccessible");
      var onlyReflectionResult = onlyReflectionMethod.invoke(onlyReflectionClass.getDeclaredConstructor().newInstance());

      println("Calling OnlyByReflection results in " + onlyReflectionResult);
    } catch(Exception e) {
      System.err.println(e.getMessage());
    }
  }

  private static void callServiceImplementation() {

    // ServiceLoader will find all implementations of the Interface in the class/module-path

    var serviceLoaderResult = ServiceLoader.load(ExposedInterface.class)
        .findFirst()
        .map(ExposedInterface::interfaceMethod)
        .orElse("Nothing");

    println("Calling ExposedInterface using ServiceLoader resulted in " + serviceLoaderResult);
  }

}