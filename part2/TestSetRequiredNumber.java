import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class TestSetRequiredNumber
{

  WorkSchedule schedule;
  WorkSchedule schedule2;
  WorkSchedule schedule3;

  @BeforeEach
  public void setup()
  {

    schedule = new WorkSchedule(4);
    schedule2 = new WorkSchedule(4);
    schedule3 = new WorkSchedule(4);

    schedule3.setRequiredNumber(2, 1, 3);
    schedule3.addWorkingPeriod("Joe", 1, 3);
    schedule3.addWorkingPeriod("Jimmy", 2, 3);

  }

  /** Test Suite  -> Testing for schedule is unchanged (starttime > endtime) */

  /**
   * TEST 1:
   * nemployee > 0, starttime > endtime
   */
  @Test
  public void test1()
  {
    schedule.setRequiredNumber(1, 3, 2);
    Assertions.assertEquals(schedule.readSchedule(2).requiredNumber, 0);
    Assertions.assertEquals(schedule.readSchedule(2).workingEmployees.length, 0);
  }


  /**
   * TEST2
   * nemployee = 0, starttime > endtime
   */
  @Test
  public void test2()
  {
    schedule3.setRequiredNumber(2, 3, 2);
    Assertions.assertEquals(schedule3.readSchedule(2).requiredNumber, 2);
    Assertions.assertEquals(schedule3.readSchedule(2).workingEmployees.length, 2);
  }


  /**
   * TEST3
   * nemployee < 0, starttime > endtime
   */
  @Test
  public void test3()
  {
    schedule3.setRequiredNumber(1, 3, 2);
    Assertions.assertEquals(schedule3.readSchedule(2).requiredNumber, 2);
    Assertions.assertEquals(schedule3.readSchedule(2).workingEmployees.length, 2);
  }

  /**
   * TEST4
   * nemployee = 0, starttime > endtime
   */
  @Test
  public void test4()
  {
    schedule3.setRequiredNumber(0, 3, 2);
    Assertions.assertEquals(schedule3.readSchedule(2).requiredNumber, 2);
    Assertions.assertEquals(schedule3.readSchedule(2).workingEmployees.length, 2);
  }

  /**
   * Testing for starttime < endtime
   */


  /**
   * TEST 5: nemployee > workingemployee and starttime > endtime
   */
  @Test
  public void testSetRequiredNumberScheduleNotEmpty()
  {
    schedule2.setRequiredNumber(2, 2, 3);
    Assertions.assertEquals(schedule2.readSchedule(2).requiredNumber, 2); // Sets requiredNumber to nemployee
    Assertions.assertEquals(schedule2.readSchedule(2).workingEmployees.length, 0); // Unchanged
  }

  // [0][0][0][0]
  // [2][3][][] , 2
  // [3][2][][] , 3 <--- should not succeed.
  // return [2][3][][] "Unchanged"


  /** ---------- PARTITION 2 a --------------
   * Test nemployee >= WorkingEmployee
   * Test starttime <= endtime
   */







  /** ---------- PARTITION 2 b --------------
   * Test nemployee < WorkingEmployee
   * Test starttime <= endtime
   */

  /**
   * Most likely a bug in the if case for WorkEmployee > nemployee.
   * Most likely the "if then" case drops the entire list, instead of reducing it to the size of nemployee.
   */


  /**
   * TEST 6 starttime < endtime, nemployee < workingemployee
   */
  @Test
  public void testWorkingEmployeesLargerThanNemployee()
  {
    schedule3.setRequiredNumber(1, 2, 3);
    Assertions.assertEquals(schedule3.readSchedule(2).requiredNumber, 1);
    Assertions.assertEquals(schedule3.readSchedule(2).workingEmployees.length, 1);
  }


  /**
   * TEST 7: nemployee = 0, starttime < endtime
   */
  @Test
  public void testWorkingEmployeesOnlyHasOneWorker()
  {
    /** Setup */
    WorkSchedule schedule4 = new WorkSchedule(4);
    schedule4.setRequiredNumber(1, 1, 3);
    schedule4.addWorkingPeriod("Joe", 1, 3);

    schedule4.setRequiredNumber(0, 2, 3);
    Assertions.assertEquals(schedule4.readSchedule(2).requiredNumber, 0);
    Assertions.assertEquals(schedule4.readSchedule(2).workingEmployees.length, 0);
  }


  //----------------- BORDER CASES

  /**
   * Starttime == Endtime, which would be a single hour only that we require workers for.
   *
   *
   * TEST: 8, nemployee = workingEmployee, starttime = endtime
   */
  @Test
  public void testStartTimeEqualEndTime()
  {
    schedule3.setRequiredNumber(2, 2, 2);

    Assertions.assertEquals(schedule3.readSchedule(2).requiredNumber, 2);
    Assertions.assertEquals(2, schedule3.readSchedule(2).workingEmployees.length);
  }


  /**
   * TEST 9
   */
  @Test
  public void TEST9()
  {
    schedule3.setRequiredNumber(3, 2, 2);

    Assertions.assertEquals(schedule3.readSchedule(2).requiredNumber, 3);
    Assertions.assertEquals(2, schedule3.readSchedule(2).workingEmployees.length);
  }


  /**
   * TEST 10
   *
   * Bug detected, should return a list with only 1 worker, drops the whole list.
   */
  @Test
  public void TEST10()
  {
    schedule3.setRequiredNumber(1, 2, 2);

    Assertions.assertEquals(schedule3.readSchedule(2).requiredNumber, 1);
    Assertions.assertEquals(1, schedule3.readSchedule(2).workingEmployees.length);
  }


  /**
   * TEST 11
   */
  @Test
  public void TEST11()
  {
    schedule3.setRequiredNumber(0, 2, 2);

    Assertions.assertEquals(schedule3.readSchedule(2).requiredNumber, 0);
    Assertions.assertEquals(0, schedule3.readSchedule(2).workingEmployees.length);
  }


  /**
   * TEST 12
   */
  @Test
  public void TEST12()
  {
    schedule3.setRequiredNumber(2, 1, 2);

    Assertions.assertEquals(schedule3.readSchedule(2).requiredNumber, 2);
    Assertions.assertEquals(2, schedule3.readSchedule(2).workingEmployees.length);
  }




  /**
   * Test negative starttime
   */
  @Test
  public void testOutOfBoundsStartEndTime()
  {
    schedule.setRequiredNumber(1, -1, 2);  // Negative starttime
    Assertions.assertEquals(schedule.readSchedule(0).requiredNumber, 0); // Ensure schedule remains unchanged
  }

  /**
   * Test endtime exceeding size-1
   * This test fails (index out of bound) which indicates that the function setRequiredNumber does not have a validation
   * for checking that endtime out of bounds.
   */
  @Test
  public void testEndTimeOutOfBounds()
  {
    schedule.setRequiredNumber(1, 1, 10);
    Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> schedule.setRequiredNumber(1,1,10));
  }


}