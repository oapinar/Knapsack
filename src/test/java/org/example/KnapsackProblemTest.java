package org.example;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.List;

/**
 * Unit test for simple App.
 */
public class KnapsackProblemTest
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public KnapsackProblemTest(String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( KnapsackProblemTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }

    // New test case for isTrivial method
    public void testIsTrivial() {
        // Create a knapsack instance where the capacity is greater than the total weight of all items
        KnapsackProblem.KnapsackInstance instance1 = new KnapsackProblem.KnapsackInstance(List.of(new KnapsackProblem.Item(1, 2, 3), new KnapsackProblem.Item(2, 3, 4)), 10);
        assertTrue(KnapsackProblem.isTrivial(instance1));

        // Create a knapsack instance where the capacity is less than the total weight of all items
        KnapsackProblem.KnapsackInstance instance2 = new KnapsackProblem.KnapsackInstance(List.of(new KnapsackProblem.Item(1, 2, 3), new KnapsackProblem.Item(2, 3, 4)), 5);
        assertFalse(KnapsackProblem.isTrivial(instance2));
    }

}
