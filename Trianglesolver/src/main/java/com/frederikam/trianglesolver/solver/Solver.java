package com.frederikam.trianglesolver.solver;

import com.frederikam.trianglesolver.Triangle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Solver {

    private static final Logger log = LoggerFactory.getLogger(Solver.class);

    public static Triangle solve(Triangle triangle) {
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
        if (triangle.getSideCount() != 3) {
            return triangle;
        } else {
            return CosinusSolver.calcAllAngles(triangle);
        }
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
        // Validate the angles in case of invalid input
        Double sum = 0d;
        sum += triangle.get(Triangle.ComponentType.ANGLE_A).value;
        sum += triangle.get(Triangle.ComponentType.ANGLE_B).value;
        sum += triangle.get(Triangle.ComponentType.ANGLE_C).value;

        Double diff = 180d - sum;
        if(Math.abs(diff) > 0.00000001d) {
            log.error("The sum of all angles must be 180 degrees");
            return null;
        }

        int sideCount = triangle.getSideCount();
        if(sideCount == 0 || sideCount == 3) {
            return triangle;
        } else if (sideCount == 1) {
            // TODO: 30-May-17
            return triangle;
        } else {
            // 2
            return CosinusSolver.solveLastSide(triangle);
        }

    }

}
