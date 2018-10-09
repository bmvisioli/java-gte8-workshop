import modules.exposed.ExposedInterface;
import modules.hidden.HiddenExposeInterfaceImpl;

module content {
  requires java.base; // There's always an implicit dependency on this module
  requires java.management;
  requires java.net.http;

  // Expose to the
  exports modules.exposed;
  exports boilerplate;

  // Expose using reflection
  opens modules.reflection;

  // Expose using SPI - Service Provider Interface
  provides ExposedInterface with HiddenExposeInterfaceImpl;

  /* If we want to expose class to one specific module:

   exports provider.exposed to dependent;‹
   opens provider.reflection to dependent;

   Normally to the project own packages;
  */
}