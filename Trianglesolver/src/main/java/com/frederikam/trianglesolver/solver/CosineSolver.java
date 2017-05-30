package com.frederikam.trianglesolver.solver;

import com.frederikam.trianglesolver.Triangle;

import java.util.List;

class CosineSolver {

    static Triangle solveLastSide(Triangle triangle) {
        Triangle.ComponentType side = triangle.getNextRemainingSide();
        Triangle.Component opposingAngle = triangle.getOpposing(side);
        List<Triangle.Component> feet = triangle.getFeet(side);
        triangle.put(side, calcOpposingSide(opposingAngle.value, feet.get(0).value, feet.get(1).value));
        return triangle;
    }

    private static Double calcOpposingSide(double opposingAngle, double s1, double s2) {
        opposingAngle = Math.toRadians(opposingAngle);

        return Math.sqrt(Math.pow(s1, 2) + Math.pow(s2, 2) - 2 * s1 * s2 * Math.cos(opposingAngle));
    }

    static Triangle calcAllAngles(Triangle triangle) {
        if(triangle.getSidesCount() != 3) {
            throw new IllegalArgumentException("All 3 sides must be known");
        }

        while (triangle.getAnglesCount() != 3) {
            Triangle.ComponentType nextAngle = triangle.getNextRemainingAngle();
            Triangle.Component opposingSide = triangle.getOpposing(nextAngle);
            List<Triangle.Component> feet = opposingSide.getFeet();
            triangle.put(nextAngle, calcAngle(opposingSide.value, feet.get(0).value, feet.get(1).value));
        }

        return triangle;
    }

    private static Double calcAngle(double opposingSide, double s1, double s2) {
        double rad = Math.acos(
                (Math.pow(s2, 2) + Math.pow(s1, 2) - Math.pow(opposingSide, 2))
                /
                (2 * s1 * s2)
        );
        return Math.toDegrees(rad);
    }

}
