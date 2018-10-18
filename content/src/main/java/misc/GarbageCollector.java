package misc;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GarbageCollector {

  public static void main(String[] args) throws IOException {

    /*
       ______                    __
      / ____/  ____ _   _____   / /_   ____ _   ____ _  ___
     / / __   / __ `/  / ___/  / __ \ / __ `/  / __ `/ / _ \
    / /_/ /  / /_/ /  / /     / /_/ // /_/ /  / /_/ / /  __/
    \____/   \__,_/  /_/     /_.___/ \__,_/   \__, /  \___/
                                             /____/
          ______           __    __                __
         / ____/  ____    / /   / /  ___   _____  / /_  ____    _____
        / /      / __ \  / /   / /  / _ \ / ___/ / __/ / __ \  / ___/
       / /___   / /_/ / / /   / /  /  __// /__  / /_  / /_/ / / /
       \____/   \____/ /_/   /_/   \___/ \___/  \__/  \____/ /_/


      G1
        Java 7 introduced the G1 garbage collector and with Java 9
        it became the default. G1 stands for Garbage First and has this
        name because it'll sweep first areas with more memory to be freed.
        In Java 10 it was made full parallel.

        Before Java 9 it could be enabled using -XX:+UseG1GC

        java -XX:+UseG1GC -Xlog:gc:stdout:time content/src/main/java/misc/GarbageCollector.java

      Epsilon
        Java 11 introduced two new experimental GCs, the first is Epsilon
        which does no garbage collection... just memory allocation.

        It can be enabled using:
          -XX:+UnlockExperimentalVMOptions -XX:+UseEpsilonGC

        java -XX:+UnlockExperimentalVMOptions -XX:+UseEpsilonGC -Xlog:gc:stdout:time content/src/main/java/misc/GarbageCollector.java

      ZGC
        The second is the Z garbage collector which is prepared to deal with
        enormous heaps of many TBs with very low pauses (~10ms);

        To enable: -XX:+UnlockExperimentalVMOptions -XX:+UseZGC.

        ATM ONLY for Linux/x64!

        java -XX:+UnlockExperimentalVMOptions -XX:+UseZGC -Xlog:gc:stdout:time content/src/main/java/misc/GarbageCollector.java

      CMS GC:
        java -XX:+UseConcMarkSweepGC -Xlog:gc:stdout:time content/src/main/java/misc/GarbageCollector.java
      Parallel GC:
        java -XX:+UseParallelGC -Xlog:gc:stdout:time content/src/main/java/misc/GarbageCollector.java
      Serial GC:
        java -XX:+UseSerialGC -Xlog:gc:stdout:time content/src/main/java/misc/GarbageCollector.java

      Selecting a collector:
        https://docs.oracle.com/en/java/javase/11/gctuning/available-collectors.html#GUID-414C9D95-297E-4EE3-B0D9-36F158A83393

     */

    ManagementFactory.getGarbageCollectorMXBeans()
        .forEach(gb -> System.out.println("Garbage Collector in Use: " + gb.getName()));

    // Pause so we can see the GC
    System.in.read();

    // And now let's test our GC creating some millions of Objects
    var now = Instant.now();

    var memUsage = IntStream.range(0, 50 * kk)
        .parallel()
        .boxed()
        .map(BigDecimal::valueOf)
        .map(GarbageCollector::getHeapUsage)
        .collect(Collectors.summarizingLong(Long::longValue));

    var then = Instant.now();
    var duration = Duration.between(now, then);

    System.out.println(("Duration " + duration.getSeconds() + "s, memory usage " + memUsage));

  }

  static int kk = 1000000;
  static int mb = 1024 * 1024;
  static Runtime runtime = Runtime.getRuntime();

  public static long getHeapUsage(BigDecimal count) {
    // This is not precise at all, just indicative
    var intCount = count.intValue();
    var freeMem = runtime.freeMemory();
    var totalMemory = runtime.totalMemory();
    var usedMem = (totalMemory - freeMem) / mb;
    if(intCount % kk == 0) System.out.println(("Heap used " + usedMem + " MB"));
    return usedMem;
  }

}
