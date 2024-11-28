package part1.test_insert;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import part1.Set;


public class InsertTests
{

    private Set setEmpty;
    private Set setFilled;

    @BeforeEach
    void setup() {

        setEmpty = new Set();

        setFilled = new Set();
        setFilled.insert(1);
        setFilled.insert(2);
        setFilled.insert(4);
        setFilled.insert(5);
    }


    @Test
    void testLoopNotExecuting() {
        setEmpty.insert(1);
        int[] expected = { 1 };
        Assertions.assertArrayEquals(expected, setEmpty.toArray());
    }



    @Test
    void testLoopExecuteAndExitEarly() {
        setEmpty.insert(5);
        setEmpty.insert(1);
        int[] expected = { 1, 5 };
        Assertions.assertArrayEquals(expected, setEmpty.toArray());
    }

    /**
     * Test for when the loop runs full iteration, adding a 6 to the end of the set.
     */
    @Test 
    void testLoopExecutesFully(){
        setFilled.insert(6);
        int [] expected = {1,2,4,5,6};
        Assertions.assertArrayEquals(expected, setFilled.toArray());
    }


    /**
     * Test for duplicate (insert 4) into a set that already has a 4.
     */
    @Test 
    void testDuplicate (){
        setFilled.insert(4);
        int [] expected = {1,2,4,5};
        Assertions.assertArrayEquals(expected, setFilled.toArray());
    }


    @Test 
    void testInsertBetween(){
        setFilled.insert(3);
        int [] expected = {1,2,3,4,5};
        Assertions.assertArrayEquals(expected, setFilled.toArray());
    }
}
