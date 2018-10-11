#!/usr/bin/env java --source 11 -XX:+UnlockExperimentalVMOptions -XX:+UseEpsilonGC

/*
     _____    __            __
    / ___/   / /_   ___    / /_   ____ _   ____    ____ _   _____
    \__ \   / __ \ / _ \  / __ \ / __ `/  / __ \  / __ `/  / ___/
   ___/ /  / / / //  __/ / /_/ // /_/ /  / / / / / /_/ /  (__  )
  /____/  /_/ /_/ \___/ /_.___/ \__,_/  /_/ /_/  \__, /  /____/
                                              /____/

  Java 11 introduced a mode to the java launcher to compile and execute a file
  effectively enabling us to create Java Scripts!

*/

public class Main {

  public static void main(String[] args) {

    var name = args.length > 0 ? args[0] : "you";

    System.out.println("Hey " + name + ", I'm a Java Script and this will cause no confusion at all.");

  }

}