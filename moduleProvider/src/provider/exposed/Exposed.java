package provider.exposed;

import provider.hidden.Hidden;

public class Exposed {

  public static String exposed() {

    return "Public code that makes use of " + Hidden.hidden();
  }

}

