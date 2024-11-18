package part1.test_insert;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import part1.Set;

/**
 * ### For full statement coverage, create test scenarios for:
 * 1. Loop doesnt execute at all (a is empty)
 * 2. Loop executes and exits early (breaks inside due to condition)
 * 3. Loop executes fully without any break being triggered.
 */
public class StatementCoverage {

    private Set set;
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
    
    void setupSet(){

    }

    /**
     * If the loop does not execute at all, the final array should only have 1. One
     * element and 2. the inserted element
     * 
     * UPDATE: After modification to the insert method, the case for empty array will not reach the loop, and the element will be added directly.
     */
    @Test
    void testLoopNotExecuting() {
        setEmpty.insert(1);
        int[] expected = { 1 };
        Assertions.assertArrayEquals(expected, setEmpty.toArray());
        Assertions.assertTrue(setEmpty.member(1));
    }

    /**
     * Design a test for the case when the loop breaks early (the if is true) in
     * `if(a.get(i) > x)`, this means setting up `a` with values so that `a.get(i) >
     * x` is true for the first element, then call insert with a value smaller than
     * the first element in `a`, basically we want the for loop to break by hitting
     * a "break" statement, this is achieved by having an entry in the array that is
     * larger than x, which will make the condition `if(a.get(i) > x` true, causing
     * an insert and a break. We test this later by asserting that due to the early
     * break, the x should be added first in the list (for simple test case where we
     * know all values in the list are larger than x).
     */

    @Test
    void testLoopExecuteAndExitEarly() {
        setEmpty.insert(5);
        setEmpty.insert(1);
        int[] expected = { 1, 5 };
        Assertions.assertArrayEquals(expected, setEmpty.toArray());
        Assertions.assertTrue(setEmpty.member(1));
        Assertions.assertTrue(setEmpty.member(5));
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
