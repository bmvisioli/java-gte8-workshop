#!/usr/bin/env java --source 11 -XX:+UnlockExperimentalVMOptions -XX:+UseEpsilonGC

public class Main {

  public static void main(String[] args) {

    var name = args.length > 0 ? args[0] : "you";

    System.out.println("Hey " + name + ", I'm a Java Script and this will cause no confusion at all.");

  }

}