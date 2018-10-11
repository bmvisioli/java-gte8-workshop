package modules.exposed;

public interface ExposedInterface {

  /*
    This interface is exposed but not its implementation classes.

    It is meant to be used with SPI and the keyword "provides".
   */

  String interfaceMethod();

}
