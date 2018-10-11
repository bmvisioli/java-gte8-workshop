package modules.reflection;

public class OnlyByReflection {

  /* This class and its methods are only visible by Reflection outside the module */

  public static String reflectivelyAccessible() {
    return "accessible only by reflection";
  }

}
