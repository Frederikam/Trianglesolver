package com.frederikam.trianglesolver;


import com.frederikam.trianglesolver.solver.Solver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TriangleTester {

    private static final Logger log = LoggerFactory.getLogger(TriangleTester.class);

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
            Assertions.assertEquals(d1, d2);
            return;
        }

        Assertions.assertTrue(Math.abs(d1 - d2) < 0.0000001);
    }

}
