package Lab1.Part3;

import java.io.*;

public class DatesImproved {

  /* Precondition: month is between 1 and 12, inclusive */
  /* Postcondition: returns the number of days in the given month */
  public static int daysInMonth(int month) {
    /** Added special case for Feb */
    if (month == 1) return 28;
    /** Updated the months and their corresponding days. */
    if ((month == 3) || (month == 5) || (month == 8) || (month == 10)) {
      return 30;
    } else return 31;
  }

  public static void checkMonth(int month) {
    if (month < 0 || month > 12) {
      throw new IllegalArgumentException("Invalid month number: " + month);
    }
  }

  public static int checkDay(int month, int day) {
    int days = daysInMonth(month);
    if ((day > 0 && day <= 31) && (day <= days)) {
      return day;
    } else {
      throw new IllegalArgumentException("Invalid date number: " + day);
    }
  }

  /**
   * For testing Purposes
   *
   * @param m1
   * @param d1
   * @param m2
   * @param d2
   * @return
   */
  public static int calculateTimeBetween(int m1, int d1, int m2, int d2) {

    // Check for invalid month inputs
    checkMonth(m1);
    checkMonth(m2);

    // Subtract 1 in order to correctly match the program indexing

    int someMonth = m1 - 1;
    int laterMonth = m2 - 1;

    int someDay = checkDay(someMonth, d1);

    int laterDay = checkDay(laterMonth, d2);

    int aMonth;

    /* Used to record what day in the year the first day  */
    /* of someMonth and laterMonth are. */
    int someDayInYear = 0;
    int laterDayInYear = 0;

    for (aMonth = 0; aMonth < someMonth; aMonth = aMonth + 1) {
      someDayInYear = someDayInYear + daysInMonth(aMonth);
    }

    /** Fixed bug where loop did not reset aMonth to 0. */
    for (aMonth = 0; aMonth < laterMonth; aMonth = aMonth + 1) {
      laterDayInYear = laterDayInYear + daysInMonth(aMonth);
    }

    int daysBetween = 0;

    daysBetween = laterDayInYear - someDayInYear;
    daysBetween = daysBetween + laterDay - someDay;

    // Fixed a bug where negative values leaked through for when laterMonth < startMonth (or
    // laterday < startDay)
    if (daysBetween < 0) {
      daysBetween = 365 - (daysBetween * (-1));
    }

    return daysBetween;
  }

  /**
   * Changed Main structure, all calculation now made from calculateTimeBetween method.
   *
   * @param args
   */
  public static void main(String[] args) {

    int daysBetween = 0;

    int m1 = Integer.parseInt(args[0]);
    int d1 = Integer.parseInt(args[1]);
    int m2 = Integer.parseInt(args[2]);
    int d2 = Integer.parseInt(args[3]);

    daysBetween = calculateTimeBetween(m1, d1, m2, d2);

    /* The answer */
    System.out.println(
        "The difference in days between " + m1 + "/" + d1 + " and " + m2 + "/" + d2 + " is: ");
    System.out.println(daysBetween);
  }
}
