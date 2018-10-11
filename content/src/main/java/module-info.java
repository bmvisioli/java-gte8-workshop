import modules.exposed.ExposedInterface;
import modules.hidden.HiddenExposeInterfaceImpl;

/*
      __  ___              __            __
     /  |/  /  ____   ____/ /  __  __   / /  ___    _____
    / /|_/ /  / __ \ / __  /  / / / /  / /  / _ \  / ___/
   / /  / /  / /_/ // /_/ /  / /_/ /  / /  /  __/ (__  )
  /_/  /_/   \____/ \__,_/   \__,_/  /_/   \___/ /____/

  Java 9 introduced JPMS - the Java Platform Modules System
  It provides a new way of organizing code - in Modules! -
  and modules enable new forms of encapsulation, not at a class
  level like public/private methods but one with public/private
  packages!

 */

module content {
  requires java.base; // There's always an implicit dependency on this module
  requires java.management;
  requires java.net.http;

  // Exposes these packages
  exports modules.exposed;
  exports boilerplate;

  // Exposes using reflection
  opens modules.reflection;

  // Exposes  using SPI - Service Provider Interface
  provides ExposedInterface with HiddenExposeInterfaceImpl;

  /* If we want to expose class to one specific module:

   exports provider.exposed to dependent;
   opens provider.reflection to dependent;

   Normally to the project own packages;
  */
}