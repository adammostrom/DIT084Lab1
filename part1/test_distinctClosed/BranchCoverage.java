package part1.test_distinctClosed;

import java.util.function.IntBinaryOperator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import part1.Set;
public class BranchCoverage {
    
/**
 * ### For full statement coverage, create test scenarios for:
 * 1. Loop doesnt execute at all (a is empty)
 * 2. Loop executes and exits early (breaks inside due to condition)
 * 3. Loop executes fully without any break being triggered.
 */

    private Set setEmptyA;
    private Set setA;

    private Set setEmptyB;
    private Set setB;

    private Set setC;

    IntBinaryOperator add;
    IntBinaryOperator sub;
    IntBinaryOperator mul;

    
    @BeforeEach
    void setup() {

        add  = (x,y) -> x + y;
        sub = (x,y) -> x - y;
        mul = (x,y) -> x * y;
    

        setA = new Set();
        setB = new Set();
        setEmptyA = new Set();
        setEmptyB = new Set();


        setC = new Set();
        setC.insert(0);
        setC.insert(1);
        setC.insert(-1);

        /* A closed set  */
        setA.insert(0);

        /* Not a closed set */
        setB.insert(2);
        setB.insert(4);

    }

    /**
     * A set with the only member "0" is a closed set on addition.
     */
    @Test 
    void testForZeroAdd(){
        Assertions.assertTrue(setA.distinctClosed(add));
    }

    @Test 
    void testForZeroSub(){
        Assertions.assertTrue(setA.distinctClosed(sub));
    }

    @Test 
    void testForZeroMul(){
        Assertions.assertTrue(setA.distinctClosed(mul));
    }


    /** EMPTY SET  */
    @Test 
    void testForEmptyAdd(){
        Assertions.assertTrue(setEmptyA.distinctClosed(add));
    }

    @Test 
    void testForEmptySub(){
        Assertions.assertTrue(setEmptyA.distinctClosed(sub));
    }

    @Test 
    void testForEmptyMul(){
        Assertions.assertTrue(setEmptyA.distinctClosed(mul));
    }


    /* FOR SET THAT IS NOT CLOSED */

    @Test 
    void testForFalseAdd(){
        Assertions.assertFalse(setB.distinctClosed(add));
    }

    @Test 
    void testForFalseSub(){
        Assertions.assertFalse(setB.distinctClosed(sub));
    }

    @Test 
    void testForFalseMul(){
        Assertions.assertFalse(setB.distinctClosed(mul));
    }


    @Test 
    void testForMul(){
        Assertions.assertTrue(setC.distinctClosed(mul));
    }

    /* According Patrick Da Silva on Mathmematics.stackExchange the operator does NOT have to only be applied to distinct elements */
    @Test 
    void testForAdd(){
        Assertions.assertTrue(setC.distinctClosed(add));
    }

    @Test 
    void testForSub(){
        Assertions.assertFalse(setC.distinctClosed(sub));
    }






    
}
