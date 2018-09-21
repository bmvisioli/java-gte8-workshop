package consumer;

import java.util.ServiceLoader;
import provider.exposed.Exposed;
import provider.exposed.ExposedInterface;

public class External {

  public static void main(String args[]) {

    System.out.println(Exposed.exposed());
    // System.out.println("trying to access " + Hidden.hidden());

    // callHiddenReflexibly();
    // callOnlyByReflection();
    // callServiceImplementation();

  }

  public Exposed run() {
    return new Exposed();
  }

  private static void callHiddenReflexibly() {
    try {
      var hiddenReflect = Class.forName("com.lunatech.modularized.hidden.Hidden");
      var hiddenMethod = hiddenReflect.getMethod("hidden", null);
      var hiddenResult = hiddenMethod.invoke(hiddenReflect.newInstance());

      System.out.println("Calling Hidden reflectively results in " + hiddenResult);
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  private static void callOnlyByReflection() {
    try {
      var onlyReflection = Class.forName("com.lunatech.modularized.reflection.OnlyByReflection");
      var onlyReflectionMethod = onlyReflection.getMethod("reflectivelyAccessible", null);
      var onlyReflectionResult = onlyReflectionMethod.invoke(onlyReflection.newInstance());

      System.out.println("Calling OnlyByReflection results in " + onlyReflectionResult);
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  private static void callServiceImplementation() {
    var serviceLoderResult = ServiceLoader.load(ExposedInterface.class)
        .findFirst()
        .map(ExposedInterface::interfaceMethod) // should return "Implementation!"
        .orElse("Nothing");

    System.out.println("Calling ExposedInterface using ServiceLoader resulted in " + serviceLoderResult);
  }

}