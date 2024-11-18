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
    if (month >= 1 && month <= 12) {
      return month;
    } else {
      throw new IllegalArgumentException("Invalid month number: " + month);
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


  public static void main (String[] args) {
    int someMonth, someDay;
    int laterMonth, laterDay;

    int aMonth;
    /**
     * Changed month so indexing makes more sense (january = 1, feb = 2 and so forth).
     */

    someMonth = checkMonth(Integer.parseInt(args[0]));
    someDay = checkDay(Integer.parseInt(args[1]), someMonth);

    laterMonth = checkMonth(Integer.parseInt(args[2]) - 1);
    laterDay = checkDay(Integer.parseInt(args[3]), laterMonth);

    /* Used to record what day in the year the first day  */
    /* of someMonth and laterMonth are. */
    int someDayInYear = 0;
    int laterDayInYear = 0;

    for (aMonth = 0; aMonth < someMonth; aMonth = aMonth + 1) {
      someDayInYear = someDayInYear + daysInMonth(aMonth);
    }



    /**
     * FIXED BUG
     */
    for (aMonth = 0; aMonth < laterMonth; aMonth = aMonth + 1) {
      laterDayInYear = laterDayInYear + daysInMonth(aMonth);
    }

    /* The answer */
    int daysBetween = 0;
    System.out.println("The difference in days between " +
        someMonth + "/" + someDay + " and " +
        laterMonth + "/" + laterDay + " is: ");
    daysBetween = laterDayInYear - someDayInYear;
    daysBetween = daysBetween + laterDay - someDay;
    System.out.println(daysBetween);
  }
}
