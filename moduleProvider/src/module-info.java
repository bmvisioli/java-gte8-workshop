import provider.exposed.ExposedInterface;
import provider.hidden.HiddenInterfaceImpl;

module provider {
  requires java.base; // Unnecessary, just making it explicit

  exports provider.exposed;
  // exports provider.exposed to dependent;

  opens provider.reflection;
  // opens provider.reflection to dependent;

  provides ExposedInterface with HiddenInterfaceImpl;
}