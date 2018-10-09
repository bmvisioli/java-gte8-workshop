package modules.exposed;

import modules.hidden.Hidden;

public class Exposed {

  /*
  This is a class intended to be directly used or instanced by the
  consumer of this module.

  Pretty much like a public method of
   */

  public static String exposed() {

    return "Public code that makes use of " + Hidden.hidden();
  }

}

