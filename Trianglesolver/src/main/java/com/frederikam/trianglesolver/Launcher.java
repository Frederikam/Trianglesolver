package com.frederikam.trianglesolver;

import com.frederikam.trianglesolver.solver.Solver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Launcher {

    private static final Logger log = LoggerFactory.getLogger(Launcher.class);

    public static void main(String[] args) {
        Triangle triangle = new Triangle(23d, 50d, null, null, null, null);
        triangle = Solver.solve(triangle);

        log.info("Solved triangle: " + triangle.toString());
    }

}
