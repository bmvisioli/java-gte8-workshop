package modules.hidden;

import modules.exposed.ExposedInterface;

public class HiddenExposeInterfaceImpl implements ExposedInterface {

  @Override
  public String interfaceMethod() {
    return "implementation";
  }
}
