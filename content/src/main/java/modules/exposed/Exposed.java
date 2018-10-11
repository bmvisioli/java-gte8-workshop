package modules.exposed;

import modules.hidden.Hidden;

public class Exposed {

  /*
    This is a class intended to be directly used by the
    clients of this module.

    Pretty much like a public method.
   */

  public static String exposed() {

    return "Public code that makes use of " + Hidden.hidden();
  }

}

