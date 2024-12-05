package Lab1.part1;


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
public class IntersectTests
{

    private Set setEmptyA;
    private Set setFilledA;

    private Set setEmptyB;
    private Set setFilledB;

    private Set testSet;
    private Set testSet2;

    @BeforeEach
    void setup() {

        setFilledA = new Set();
        setFilledB = new Set();

        setEmptyA = new Set();
        setEmptyB = new Set();

        testSet2 = new Set();
        testSet = new Set();

        setFilledA.insert(1);
        setFilledA.insert(2);
        setFilledA.insert(8);
        setFilledA.insert(9);

        setFilledB.insert(1);
        setFilledB.insert(5);
        setFilledB.insert(7);
        setFilledB.insert(9);

        // Resubmission modification
        testSet.insert(0);

        testSet2.insert(0);
        testSet2.insert(1);


    }


    /**
     * Test for empty set.
     */
    @Test 
    void testInputEmpty(){
        int [] expected = {};
        setEmptyA.intersect(setEmptyB);
        Assertions.assertArrayEquals(expected,setEmptyA.toArray() );
    }

    @Test 
    void testAEmpty(){
        int [] expected = {};
        setEmptyA.intersect(setFilledB);
        Assertions.assertArrayEquals(expected, setEmptyA.toArray());
    }

    @Test 
    void testBEmpty(){
        int [] expected = {};
        setEmptyB.intersect(setFilledA);
        Assertions.assertArrayEquals(expected, setEmptyB.toArray());
    }


    @Test 
    void testFilledA(){
        int [] expected = {1,9};
        setFilledA.intersect(setFilledB);
        Assertions.assertArrayEquals(expected, setFilledA.toArray());
    }

    @Test 
    void testFilledB(){
        int [] expected = {1,9};
        setFilledB.intersect(setFilledA);
        Assertions.assertArrayEquals(expected, setFilledB.toArray());
    }


    @Test
    void testDifferentLengths(){
        int [] expected = {0};
        testSet2.intersect(testSet);
        Assertions.assertArrayEquals(expected, testSet2.toArray());
    }



}