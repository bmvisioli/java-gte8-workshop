package misc;

import java.lang.management.ManagementFactory;

public class GarbageCollector {

  public static void main(String args[]) {

    // After Java 9 release G1 became the default garbage collector (previously ParallelI)
    ManagementFactory.getGarbageCollectorMXBeans()
        .forEach(gb -> System.out.println(gb.getName()));

  }

}
