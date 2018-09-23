package time;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class Explanation01 {

  public static void main(String args[]) {

    /*
      Ok ok, we all agree that Java had a quite terrible API for date and time.
      The year is 1900-based, the month 0-based and the day (date) is 1-based !?!??!
      So the first Christmas lunch after Lunatech was founded 25-12-1993 12:00:00 can be created as
    */
    var date = new Date(93, 11, 25, 11, 59,60);
    System.out.println(date);

    // Java 8 introduced a revisited Time API with classes like
    var localDate = LocalDate.of(1993, 12, 25);
    var localDateStr = LocalDate.parse("1993-12-25");
    var localTime = LocalTime.of(12, 0, 0);
    var localDateTime = LocalDateTime.of(localDate, localTime);

    var utcZone = ZoneId.of("UTC");
    var amsterdamZone = ZoneId.of("Europe/Amsterdam");

    var zonedDateTime = ZonedDateTime.of(localDateTime, amsterdamZone);

    System.out.println(zonedDateTime);

    // For application timestamps in milli, nano and plain seconds
    var now = Instant.now();
    var then = Instant.now();
    System.out.println("Nanoseconds between the two instants: " + now.until(then, ChronoUnit.NANOS));
    // Or
    var duration = Duration.between(now, then);
    System.out.println("Nanoseconds between the two instants: " + duration.getNano());

    // The equivalent of Duration for years, months and days is
    var today = LocalDate.now();
    var tomorrowNextYear = today.plusDays(1).plusYears(1);
    var period = Period.between(today, tomorrowNextYear);

    // But this class can be a bit misleading, the below results in 366 days (aprox.) right?
    System.out.println("Days between the two dates: " + period.getDays()); // ERRRRR, it's 1 !?

    // To get amount of timeunits better use
    var daysBetween = ChronoUnit.DAYS.between(today, tomorrowNextYear);
    System.out.println("Days between the two dates: " + daysBetween); // Ahhhhh

    // The instances of these classes are all immutable
    assert(zonedDateTime.plusDays(20) != zonedDateTime);

    // And to access it's properties
    zonedDateTime.getDayOfMonth();


  }

}
