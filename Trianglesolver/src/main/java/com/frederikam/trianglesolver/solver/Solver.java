package com.frederikam.trianglesolver.solver;

import com.frederikam.trianglesolver.Triangle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Solver {

    private static final Logger log = LoggerFactory.getLogger(Solver.class);

    public static Triangle solve(Triangle triangle) {
        log.info("Attempting to solve: " + triangle);

        switch (triangle.getAngleCount()) {
            case 0:
                return on0Angles(triangle);
            case 1:
                return on1Angle(triangle);
            case 2:
                return on2Angles(triangle);
            case 3:
                return on3Angles(triangle);
            default:
                throw new RuntimeException("No valid case");
        }
    }

    private static Triangle on0Angles(Triangle triangle) {
        //todo
        return triangle;
    }

    private static Triangle on1Angle(Triangle triangle) {
        //todo
        return triangle;
    }

    private static Triangle on2Angles(Triangle triangle) {
        List<Triangle.Component> angles = triangle.getAngles();
        Triangle.ComponentType thirdAngle = triangle.getNextRemainingAngle();

        triangle.put(thirdAngle, 180d - angles.get(0).value - angles.get(1).value);

        return on3Angles(triangle);
    }

    private static Triangle on3Angles(Triangle triangle) {
        //todo
        return triangle;
    }

}
