package com.frederikam.trianglesolver.solver;

import com.frederikam.trianglesolver.Triangle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Solver {

    private static final Logger log = LoggerFactory.getLogger(Solver.class);

    public static Triangle solve(Triangle triangle) {
        log.info("Attempting to solve: " + triangle);

        return triangle;
    }

}
