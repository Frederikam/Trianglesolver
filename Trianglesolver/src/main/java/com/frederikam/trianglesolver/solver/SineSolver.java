package com.frederikam.trianglesolver.solver;

import com.frederikam.trianglesolver.Triangle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.Math.*;

class SineSolver {

    private static final Logger log = LoggerFactory.getLogger(SineSolver.class);

    static Triangle solve(Triangle triangle) {
        // First find an opposing set of a side and an angle
        Triangle.Component setSide = null;
        Triangle.Component setAngle = null;

        for (Triangle.Component side : triangle.getSides()) {
            if (side.getOpposing().value != null) {
                setSide = side;
                setAngle = side.getOpposing();
                break;
            }
        }

        if (setSide == null) {
            return triangle;
        }

        /* We may need to pass sides multiple twice because we may depend on additional angles
          * To calculate the angles we may need to calculate the sides first
          * This is a catch all solution */
        calcSidesPass(triangle, setSide, setAngle);
        calcAnglesPass(triangle, setSide, setAngle);
        calcSidesPass(triangle, setSide, setAngle);

        // At this point we should have 2 or 3 angles. Request the solver to do the rest.
        if(triangle.getSidesCount() == 2 || triangle.getSidesCount() == 3) {
            return Solver.solve(triangle);
        } else {
            log.warn("Unexpected number of sides: " + triangle);
        }

        return triangle;
    }

    private static void calcSidesPass(Triangle triangle, Triangle.Component setSide, Triangle.Component setAngle) {
        // Look for sides to calculate
        for (Triangle.Component angle : triangle.getAngles()) {
            if (angle.getOpposing().value == null) {
                // a = (b/sin(B)) * sin(A)
                triangle.put(angle.getOpposing().type,
                        (setSide.value / sin(toRadians(setAngle.value)))
                                * sin(toRadians(angle.value))
                );
            }
        }
    }

    private static void calcAnglesPass(Triangle triangle, Triangle.Component setSide, Triangle.Component setAngle) {
        // Look for angles to calculate
        for (Triangle.Component side : triangle.getSides()) {
            if (side.getOpposing().value == null) {
                // A = asin(b * (sin(A)/a))
                triangle.put(side.getOpposing().type,
                        toDegrees(asin(
                                side.value * (sin(toRadians(setAngle.value))/setSide.value)
                        ))
                );
            }
        }
    }

}
