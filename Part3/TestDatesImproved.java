package Part3;
import static Part3.DatesImproved.calculateTimeBetween;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestDatesImproved {





  /** VALID INPUTS **/

  @Test
  public void test_Day1_EQ_Day2(){
    int result = calculateTimeBetween(1, 15, 4, 15);
    Assertions.assertEquals(31, result);
  }

  @Test
  public void second_test_Day1_EQ_Day2(){
    int result = calculateTimeBetween(4, 15, 5, 15);
    Assertions.assertEquals(30, result);
  }

  @Test
  public void test_Day1_LT_Day2_Month1_EQ_Month2(){
    int result = calculateTimeBetween(5, 5, 5, 6);
    Assertions.assertEquals(1, result);
  }

  @Test
  public void test_Day1_LT_Day2_Month1_NEQ_Month2(){
    int result = calculateTimeBetween(5, 5, 6, 6);
    Assertions.assertEquals(32, result);
  }

  @Test
  public void test_Day2_LT_Day1_Month1_EQ_Month2(){
    int result = calculateTimeBetween(4, 6, 4, 3);
    Assertions.assertEquals(362, result);
  }

  @Test
  public void test_Day2_LT_Day1_Month1_NEQ_Month2(){
    int result = calculateTimeBetween(4, 6, 5, 3);
    Assertions.assertEquals(27, result);
  }


  /**
   * Added these two to test for months.
   */
  @Test
  public void test_Day2_EQ_Day1_Month1_GT_Month2(){
    int result = calculateTimeBetween(5, 6, 3, 6);
    Assertions.assertEquals(304, result);
  }

  @Test
  public void test_Day2_NEQ_Day1_Month1_GT_Month2(){
    int result = calculateTimeBetween(5, 6, 3, 3);
    Assertions.assertEquals(301, result);
  }
  /** INVALID INPUTS **/

  @Test
  public void test_Month_LT_31_days_Day_EQ_31(){
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      DatesImproved.calculateTimeBetween(2, 31, 5, 15);
    });
  }

  @Test
  public void test_Month_GT_12(){
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      DatesImproved.calculateTimeBetween(13, 31, 5, 15);
    });
  }

  @Test
  public void test_Month_LT_1(){
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      DatesImproved.calculateTimeBetween(-1, 31, 5, 15);
    });
  }

  @Test
  public void test_Day_GT_31(){
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      DatesImproved.calculateTimeBetween(2, 60, 5, 15);
    });
  }

}
