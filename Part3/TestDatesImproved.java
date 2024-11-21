package Part3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestDatesImproved
{


  /** VALID INPUTS **/

  @Test
  public void test_Day1_EQ_Day2(){

  }
  @Test
  public void test_Day1_LT_Day2_Month1_EQ_Month2(){

  }

  @Test
  public void test_Day1_LT_Day2_Month1_NEQ_Month2(){

  }

  @Test
  public void test_Day2_LT_Day1_Month1_EQ_Month2(){

  }

  @Test
  public void test_Day2_LT_Day1_Month1_NEQ_Month2(){

  }

  /** INVALID INPUTS **/

  @Test
  public void test_Month_LT_31_days_Day_EQ_31(){
    // Expect throw error
  }

  @Test
  public void test_Month_GT_12(){
    // Expect throw error
  }

  @Test
  public void test_Month_LT_1(){
    // Expect throw error
  }

  @Test
  public void test_Day_GT_31(){
    // Expect throw error
  }

}
