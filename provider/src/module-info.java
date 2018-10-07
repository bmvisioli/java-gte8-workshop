import provider.exposed.ExposedInterface;
import provider.hidden.HiddenExposeInterfaceImpl;

module provider {
  requires java.base; // There's always an implicit dependency on this module

  // Expose to the
  exports provider.exposed;

  // Expose using reflection
  opens provider.reflection;

  // Expose using SPI - Service Provider Interface
  provides ExposedInterface with HiddenExposeInterfaceImpl;

  /* If we want to expose class to one specific module:

   exports provider.exposed to dependent;‹
   opens provider.reflection to dependent;

   Normally to the project own packages;
  */
}