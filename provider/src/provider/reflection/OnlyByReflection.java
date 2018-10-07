package provider.reflection;

public class OnlyByReflection {

  /*
  This class will not appear in the classpath
   */

  public static String reflectivelyAccessible() {
    return "accessible only by reflection";
  }

}
