package part1.test_member;

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
public class BranchCoverage {

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



    @Test
    void testEmptySet(){
        Assertions.assertTrue(!setEmpty.member(1));
    }

    @Test 
    void testIsMember(){
        Assertions.assertTrue(setFilled.member(2));
    }

    @Test 
    void testLoopComplete() {
        Assertions.assertTrue(!setFilled.member(10));
    }

    @Test 
    void testMiddleMemberMissing(){
        Assertions.assertTrue(!setFilled.member(3));
    }

    
}