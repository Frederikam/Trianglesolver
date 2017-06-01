package com.frederikam.trianglesolver;


import com.frederikam.trianglesolver.solver.Solver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TriangleTester {

    private static final Logger log = LoggerFactory.getLogger(TriangleTester.class);

    @Test
    void testAlreadyCompleted() {
        Triangle input = new Triangle(60d, 60d, 60d, 5d, 5d, 5d);
        Triangle output = new Triangle(60d, 60d, 60d, 5d, 5d, 5d);
        testTriangle(input, output);
    }

    @Test
    void testInvalidAngleSum() {
        Triangle input = new Triangle(50d, 50d, 50d, null, null, null);
        testTriangle(input, null);
    }

    @Test
    void testAngleSum() {
        Triangle input = new Triangle(50d, 30d, null, null, null, null);
        Triangle target = new Triangle(50d, 30d, 100d, null, null, null);
        testTriangle(input, target);
    }

    @Test
    void testNoAngles() {
        Triangle input = new Triangle(null, null, null, 133d, 185d, 190d);
        Triangle target = new Triangle(41.51917d, 67.22587, 71.25496, 133d, 185d, 190d);
        testTriangle(input, target);
    }

    @Test
    void testNoInfo() {
        Triangle input = new Triangle(null, null, null, null, null, null);
        Triangle target = new Triangle(null, null, null, null, null, null);
        testTriangle(input, target);
    }

    @Test
    void test1Angle2Sides() {
        Triangle input = new Triangle(null, 35d, null, null, 35d, 50d);
        Triangle target = new Triangle(89.97573d, 35d, 55.02427d, 61.02063d, 35d, 50d);
        testTriangle(input, target);
    }

    @Test
    void test2Angles1Side() {
        Triangle input = new Triangle(50d, 60d, null, null, null, 5d);
        Triangle target = new Triangle(50d, 60d, 70d, 4.07603d, 4.60802d, 5d);
        testTriangle(input, target);
    }

    @Test
    void testCosineAngles() {
        Triangle input = new Triangle(50d, null, null, 5d, null, 5d);
        Triangle target = new Triangle(50d, 80d, 50d, 5d, 6.427876, 5d);
        testTriangle(input, target);
    }

    private void testTriangle(Triangle input, Triangle target) {
        log.info("Input:  " + input);
        log.info("Target: " + target);
        Triangle output = Solver.solve(input);
        log.info("Output: " + output);

        if(target == null) {
            Assertions.assertEquals(null, output);
        } else {
            for (Triangle.ComponentType type : Triangle.ComponentType.ALL) {
                assertNearlyEquals(target.get(type).value, output.get(type).value);
            }
        }
        log.info("-----------------\n");
    }

    private void assertNearlyEquals(Double d1, Double d2) {
        // This is used to account for double precision

        if(d1 == null) {
            Assertions.assertEquals(null, d2);
            return;
        }

        Assertions.assertTrue(Math.abs(d1 - d2) < 0.0001);
    }

}
