package consumer;

import java.util.ServiceLoader;
import provider.exposed.Exposed;
import provider.exposed.ExposedInterface;

public class Consumer {

  public static void main(String[] args) {

    System.out.println(Exposed.exposed());
    // System.out.println("trying to access " + Hidden.hidden());

    callHiddenByReflection();
    callOnlyByReflection();
    callServiceImplementation();

  }

  /** Tries to call a method that is not "exported" or "opened" */
  private static void callHiddenByReflection() {
    try {
      var hiddenClass = Class.forName("provider.hidden.Hidden");
      var hiddenMethod = hiddenClass.getMethod("hidden");
      var hiddenResult = hiddenMethod.invoke(hiddenClass.getDeclaredConstructor().newInstance());

      System.out.println("Calling Hidden by reflection results in " + hiddenResult);
    } catch(Exception e) {
      System.err.println(e.getMessage());
    }
  }

  private static void callOnlyByReflection() {
    try {
      var onlyReflectionClass = Class.forName("provider.reflection.OnlyByReflection");
      var onlyReflectionMethod = onlyReflectionClass.getMethod("reflectivelyAccessible");
      var onlyReflectionResult = onlyReflectionMethod.invoke(onlyReflectionClass.getDeclaredConstructor().newInstance());

      System.out.println("Calling OnlyByReflection results in " + onlyReflectionResult);
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

    System.out.println("Calling ExposedInterface using ServiceLoader resulted in " + serviceLoaderResult);
  }

}