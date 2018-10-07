package provider.hidden;

import provider.exposed.ExposedInterface;

public class HiddenExposeInterfaceImpl implements ExposedInterface {

  @Override
  public String interfaceMethod() {
    return "implementation";
  }
}
