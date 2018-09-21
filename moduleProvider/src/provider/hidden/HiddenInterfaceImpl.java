package provider.hidden;

import provider.exposed.ExposedInterface;

public class HiddenInterfaceImpl implements ExposedInterface {

  @Override
  public String interfaceMethod() {
    return "implementation";
  }
}
