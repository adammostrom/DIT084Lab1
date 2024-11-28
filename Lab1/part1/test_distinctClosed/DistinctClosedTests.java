package part1.test_distinctClosed;

import java.util.function.IntBinaryOperator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import part1.Set;
public class DistinctClosedTests
{
    


    private Set setEmpty;
    private Set setA;
    private Set setB;
    private Set setC;

    IntBinaryOperator add;
    IntBinaryOperator sub;
    IntBinaryOperator mul;

    
    @BeforeEach
    void setup() {

        add = (x,y) -> x + y;
        sub = (x,y) -> x - y;
        mul = (x,y) -> x * y;

        setEmpty = new Set();

        /* A closed set  */
        setA = new Set();
        setA.insert(0);

        setB = new Set();
        /* Not a closed set */
        setB.insert(2);
        setB.insert(4);
        
        /* Closed on multiplication, not addition */
        setC = new Set();
        setC.insert(0);
        setC.insert(1);
        setC.insert(-1);
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
        Assertions.assertTrue(setEmpty.distinctClosed(add));
    }

    @Test 
    void testForEmptySub(){
        Assertions.assertTrue(setEmpty.distinctClosed(sub));
    }

    @Test 
    void testForEmptyMul(){
        Assertions.assertTrue(setEmpty.distinctClosed(mul));
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
