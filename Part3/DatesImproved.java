package Part3;

import java.io.*;

class DatesImproved {


  /* Precondition: month is between 1 and 12, inclusive */
  /* Postcondition: returns the number of days in the given month */
  public static int daysInMonth (int month) {
    /**
     * Added special case for Feb
     */
    if(month == 1) return 28;
    /**
     * Updated the months and their corresponding days.
     */
    if ((month == 3) || (month == 5) || (month == 8) || (month == 10)) {
      return 30;
    }
    else return 31;
  }

  private static int checkMonth(int month){
    /**
     * Changed month so indexing makes more sense (january = 1, feb = 2 and so forth).
     */
    int inputMonth = month;
    month = month - 1;
    if (month >= 0 && month <= 12) {
      return month;
    } else {
      throw new IllegalArgumentException("Invalid month number: " + inputMonth);
    }
  }

  private static int checkDay (int month, int day){
    int days = daysInMonth(month);
     if ((day > 0 && day < 31) && (day < days)){
       return day;
     } else {
       throw new IllegalArgumentException("Invalid date number: " + day);
     }
  }


  /**
   * For testing Purposes
   * @param m1
   * @param d1
   * @param m2
   * @param d2
   * @return
   */
  public static int calculateTimeBetween (int m1, int d1, int m2, int d2){


    int someMonth = checkMonth(m1);
    int someDay = checkDay(m1,d1);

    int laterMonth = checkMonth(m2);
    int laterDay = checkDay(m2,d2);

    int aMonth;

    /* Used to record what day in the year the first day  */
    /* of someMonth and laterMonth are. */
    int someDayInYear = 0;
    int laterDayInYear = 0;

    for (aMonth = 0; aMonth < someMonth; aMonth = aMonth + 1) {
      someDayInYear = someDayInYear + daysInMonth(aMonth);
    }

    /**
     * Fixed bug where loop did not reset aMonth to 0.
     */
    for (aMonth = 0; aMonth < laterMonth; aMonth = aMonth + 1) {
      laterDayInYear = laterDayInYear + daysInMonth(aMonth);
    }

    int daysBetween = 0;

    daysBetween = laterDayInYear - someDayInYear;
    daysBetween = daysBetween + laterDay - someDay;

    // Fixed a bug where negative values leaked through for when laterMonth < startMonth (or laterday < startDay)
    if(daysBetween < 0) {daysBetween = 365 - (daysBetween * (-1));}


    return daysBetween;
  }


  /**
   * Changed Main structure, all calculation now made from calculateTimeBetween method.
   * @param args
   */
  public static void main (String[] args) {

    int daysBetween = 0;

    int m1 = Integer.parseInt(args[0]);
    int d1 = Integer.parseInt(args[1]);
    int m2 = Integer.parseInt(args[2]);
    int d2 = Integer.parseInt(args[3]);

    daysBetween = calculateTimeBetween(m1, d1, m2, d2);

    /* The answer */
    System.out.println("The difference in days between " +
        m1 + "/" + d1 + " and " +
        m2 + "/" + d2 + " is: ");
    System.out.println(daysBetween);
  }



}
