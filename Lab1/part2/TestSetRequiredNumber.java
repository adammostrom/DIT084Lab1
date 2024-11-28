import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class TestSetRequiredNumber
{

  WorkSchedule schedule1;
  WorkSchedule schedule2;
  WorkSchedule schedule3;

  @BeforeEach
  public void setup() {

    schedule1 = new WorkSchedule(4);
    schedule2 = new WorkSchedule(4);
    schedule3 = new WorkSchedule(4);

    schedule2.setRequiredNumber(2, 1, 3);
    schedule2.addWorkingPeriod("Joe", 1, 3);
    schedule2.addWorkingPeriod("Jimmy", 2, 3);

    schedule3.setRequiredNumber(1, 1, 3);
    schedule3.addWorkingPeriod("Joe", 1, 3);
  }

  /**
   * Test Suite:
   * st > et  st == et  st < et
   *   [1]      [5]       [9]   nemp > wemp
   *   [2]      [6]       [10]  nemp = wemp
   *   [3]      [7]       [11]  nemp < wemp
   *   [4]      [8]       [12]  nemp = 0
   */

  /**
   * TEST 1:
   * nemployee > workingEmployee, starttime > endtime
   */
  @Test
  public void test_When_StartTime_GT_Endtime_And_Nemployee_GT_WorkingEmployees() {
    schedule1.setRequiredNumber(1, 3, 2);
    Assertions.assertEquals(schedule1.readSchedule(2).requiredNumber, 0);
    Assertions.assertEquals(schedule1.readSchedule(2).workingEmployees.length, 0);
  }

  /**
   * TEST2
   * nemployee == workingEmployees, starttime > endtime
   */
  @Test
  public void test_When_StartTime_GT_Endtime_And_Nemployee_EQ_WorkingEmployees() {
    schedule2.setRequiredNumber(2, 3, 2);
    Assertions.assertEquals(schedule2.readSchedule(2).requiredNumber, 2);
    Assertions.assertEquals(schedule2.readSchedule(2).workingEmployees.length, 2);
  }

  /**
   * TEST3
   * nemployee < workingEmployee, starttime > endtime
   */
  @Test
  public void test_When_StartTime_GT_Endtime_And_Nemployee_LT_WorkingEmployees() {
    schedule2.setRequiredNumber(1, 3, 2);
    Assertions.assertEquals(schedule2.readSchedule(2).requiredNumber, 2);
    Assertions.assertEquals(schedule2.readSchedule(2).workingEmployees.length, 2);
  }

  /**
   * TEST4
   * nemployee == 0, starttime > endtime
   */
  @Test
  public void test_When_StartTime_GT_Endtime_And_Nemployee_EQ_Zero() {
    schedule2.setRequiredNumber(0, 3, 2);
    Assertions.assertEquals(schedule2.readSchedule(2).requiredNumber, 2);
    Assertions.assertEquals(schedule2.readSchedule(2).workingEmployees.length, 2);
  }

  /**
   * TEST 5
   * nemployee > workingEmployee, starttime == endtime
   */
  @Test
  public void test_When_StartTime_EQ_Endtime_And_Nemployee_GT_WorkingEmployees() {
    schedule2.setRequiredNumber(3, 2, 2);
    Assertions.assertEquals(schedule2.readSchedule(2).requiredNumber, 3);
    Assertions.assertEquals(2, schedule2.readSchedule(2).workingEmployees.length);
  }

  /**
   * TEST: 6
   * nemployee == workingEmployee, starttime == endtime
   */
  @Test
  public void test_When_StartTime_EQ_Endtime_And_Nemployee_EQ_WorkingEmployees() {
    schedule2.setRequiredNumber(2, 2, 2);
    Assertions.assertEquals(schedule2.readSchedule(2).requiredNumber, 2);
    Assertions.assertEquals(2, schedule2.readSchedule(2).workingEmployees.length);
  }

  /**
   * TEST 7
   * nemployee < workingEmployee, starttime == endtime
   * Bug detected, should return a list with only 1 worker, drops the whole list.
   */
  @Test
  public void test_When_StartTime_EQ_Endtime_And_Nemployee_LT_WorkingEmployees() {
    schedule2.setRequiredNumber(1, 2, 2);
    Assertions.assertEquals(schedule2.readSchedule(2).requiredNumber, 1);
    Assertions.assertEquals(1, schedule2.readSchedule(2).workingEmployees.length);
  }

  /**
   * TEST 8
   * nemployee == 0, starttime == endtime
   */
  @Test
  public void test_When_StartTime_EQ_Endtime_And_Nemployee_EQ_Zero() {
    schedule2.setRequiredNumber(0, 2, 2);
    Assertions.assertEquals(schedule2.readSchedule(2).requiredNumber, 0);
    Assertions.assertEquals(0, schedule2.readSchedule(2).workingEmployees.length);
  }

  /**
   * TEST 9
   * nemployee > workingemployee and starttime < endtime
   */
  @Test
  public void test_When_StartTime_LT_EndTime_And_NEmployee_GT_WorkingEmployees() {
    schedule1.setRequiredNumber(2, 2, 3);
    Assertions.assertEquals(schedule1.readSchedule(2).requiredNumber, 2); // Sets requiredNumber to nemployee
    Assertions.assertEquals(schedule1.readSchedule(2).workingEmployees.length, 0); // Unchanged
  }

  /**
   * TEST 10
   * nemployee == workingEmployee, starttime < endtime
   */
  @Test
  public void test_When_StartTime_LT_EndTime_And_NEmployee_EQ_WorkingEmployees() {
    schedule2.setRequiredNumber(2, 1, 2);
    Assertions.assertEquals(schedule2.readSchedule(2).requiredNumber, 2);
    Assertions.assertEquals(2, schedule2.readSchedule(2).workingEmployees.length);
  }


  /**
   * TEST 11
   * nemployee < workingemployee, starttime < endtime
   */
  @Test
  public void test_When_StartTime_LT_EndTime_And_NEmployee_LT_WorkingEmployees() {
    schedule2.setRequiredNumber(1, 2, 3);
    Assertions.assertEquals(schedule2.readSchedule(2).requiredNumber, 1);
    Assertions.assertEquals(schedule2.readSchedule(2).workingEmployees.length, 1);
  }


  /**
   * TEST 12
   * nemployee = 0, starttime < endtime
   */
  @Test
  public void test_When_StartTime_LT_EndTime_And_NEmployee_EQ_Zero() {
    schedule3.setRequiredNumber(0, 2, 3);
    Assertions.assertEquals(schedule3.readSchedule(2).requiredNumber, 0);
    Assertions.assertEquals(schedule3.readSchedule(2).workingEmployees.length, 0);
  }
}