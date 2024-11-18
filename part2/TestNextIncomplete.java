import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;




public class TestNextIncomplete {

  /**
   * Setup
   */
  WorkSchedule schedule;
  WorkSchedule schedule2;
  WorkSchedule schedule3;

  @BeforeEach
  public void setup(){

    schedule = new WorkSchedule(4);
    schedule2 = new WorkSchedule(4);
    schedule3 = new WorkSchedule(4);

    // Required number of workers at a certain shift (starttime 1)
    schedule3.setRequiredNumber(3,1,3);

    // Two workers in schedule 3 but only one works from 1 to 2, hence the first hour has 1 less than required.
    schedule3.addWorkingPeriod("Joe", 1,3);
    schedule3.addWorkingPeriod("Jimmy", 2, 3);
    schedule3.addWorkingPeriod("John", 2, 3);


  }
  /**
   * b) nextIncomplete
   * requires:
   *   currenttime >= 0 and currenttime < size
   * ensures:
   *   if there is an hour in the interval currenttime to size - 1 such that
   *      the length of workingEmployees is less that requiredNumber
   *   then
   *     returns the time of the hour closest to currenttime such that
   *     the length of workingEmployees is less that requiredNumber
   *   otherwise
   *     returns -1
   *   and in either case the schedule is unchanged
   */

  /**
   * ---Input Space---
   * currenttime = any int n such that: 0 <= n < size
   * size        = any int t such that: currenttime < t
   */

  /**
   * ---Partitions---
   * Interval: [currenttime, size -1]
   * Requirement: > 1 hour where hour.workingEmployees.length < requiredNumber
   * P1: There exist at least one hour in the interval that fulfills the requirement
   * P2: No hours in the interval fulfill the requirement.
   */

  /**
   * ************* PARTITION 1 *************
   */


  /**
   * TEST 1, test that required time = issue case (issue case is when the req time is > than scheduled workers)
   */
  @Test
  public void testAtLeastOneHourFulfillsRequirement(){
    int currenttime = 1; // currenttime < size (requirement)
    int expectedHour = 1; // The earliest hour in the interval [1, 3] where workingEmployees < requiredNumber (this hour only has 1 worker).

    // Assert that the hour that should be returned is the hour closes to the currenttime that still fills the req that length of workingEmployees < requiredNumber.
    Assertions.assertEquals(expectedHour, schedule3.nextIncomplete(currenttime));

    // Checking if the schedule is the same
    Assertions.assertEquals(schedule3.readSchedule(2).requiredNumber, 3);
    Assertions.assertEquals(schedule3.readSchedule(2).workingEmployees.length, 3);

  }

  /**
   * This test returns should return -1 due to the fact that the "if" statement fails, which it does because there are 3 workers available for working from 1 to 3, which means that the
   */

  /**
   * TEST 3:, should return -1 because we have all workers for the timeslot.
   */
  @Test
  public void testIncompleteFromDifferentStartTime(){
    int currenttime = 2;
    // here the length of available workers between 2 and 3 is 2, and the current time is 2, the function returns -1 (else branch).
    Assertions.assertEquals(-1, schedule3.nextIncomplete(currenttime));

    Assertions.assertEquals(schedule3.readSchedule(2).requiredNumber, 3);
    Assertions.assertEquals(schedule3.readSchedule(2).workingEmployees.length, 3);
  }

  /**
   * Edge Case.
   * Test when currenttime = 0, expects function to give us the closes hour in the schedule that is "incomplete" -> 1, as 2 workers are required for this hour.
   */
  @Test
  public void testStartAtZero(){
    int currenttime = 0;
    int expectedHour = 1;
    Assertions.assertEquals(expectedHour, schedule3.nextIncomplete(currenttime));

    // Checking if the schedule is the same
    Assertions.assertEquals(schedule3.readSchedule(2).requiredNumber, 3);
    Assertions.assertEquals(schedule3.readSchedule(2).workingEmployees.length, 3);
  }

  /**
   * Edge case.
   * Test when currenttime = size - 1 (last hour). Should return -1 since there are no more hours in the intervall when currenttime starts at the last hour, and hence cannot find any "nextIncomplete hour".
   */
  @Test
  public void testStartAtLastHour(){
    int currenttime = 3;
    Assertions.assertEquals(-1, schedule3.nextIncomplete(currenttime));
  }


  @Test
  public void testEmptySchedule() {
    Assertions.assertEquals(-1, schedule.nextIncomplete(0));
  }


  /**
   * Do we need tests to show that the function nextIncomplete does not have some valid checker for invalid entries like negative values etc?
   */
}


