import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class TestNextIncomplete {

  WorkSchedule schedule;
  WorkSchedule schedule2;
  WorkSchedule schedule3;

  WorkSchedule schedule4;


  @BeforeEach
  public void setup(){

    schedule = new WorkSchedule(4);
    schedule2 = new WorkSchedule(4);
    schedule3 = new WorkSchedule(4);


    schedule4 = new WorkSchedule(5);
    schedule4.setRequiredNumber(3, 1,3);
    schedule4.addWorkingPeriod("Jane", 1, 3);
    schedule4.addWorkingPeriod("Kim", 2, 3);
    schedule4.addWorkingPeriod("Dana", 3, 3);


    schedule3.setRequiredNumber(3,1,3);
    schedule3.addWorkingPeriod("Joe", 1,3);
    schedule3.addWorkingPeriod("Jimmy", 2, 3);
    schedule3.addWorkingPeriod("John", 2, 3);


  }


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
   * TEST 1, test that required time = issue case (issue case is when the req time is > than scheduled workers)
   */
  @Test
  public void testAtLeastOneHourFulfillsRequirement(){
    int currenttime = 1;
    int expectedHour = 1;

    Assertions.assertEquals(expectedHour, schedule3.nextIncomplete(currenttime));
    Assertions.assertEquals(schedule3.readSchedule(2).requiredNumber, 3);
    Assertions.assertEquals(schedule3.readSchedule(2).workingEmployees.length, 3);

  }

  /**
   */

  /**
   * TEST 3:, should return -1 because we have all workers for the timeslot.
   * This test returns should return -1 due to the fact that the "if" statement fails, which it does because there are 3 workers available for working from 1 to 3
   */
  @Test
  public void testIncompleteFromDifferentStartTime(){
    int currenttime = 2;

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
   * Found a bug, nextIncomplete for several incomplete working hours should return the first incomplete hour.
   *
   */
  @Test
  public void testMultipleIncompleteOnes(){
    int currenttime = 1;
    int expectedHour = 1;

    Assertions.assertEquals(expectedHour, schedule4.nextIncomplete(currenttime));
    Assertions.assertEquals(schedule4.readSchedule(1).requiredNumber, 3);
    Assertions.assertEquals(schedule4.readSchedule(1).workingEmployees.length, 1);
  }


}


