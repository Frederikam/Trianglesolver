package com.frederikam.trianglesolver;

import com.frederikam.trianglesolver.solver.Solver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Launcher {

    private static final Logger log = LoggerFactory.getLogger(Launcher.class);

    public static void main(String[] args) {
        Triangle triangle = new Triangle(null, null, null, null, null, null);
        int i = 0;

        for (String arg : args) {
            String nextArg = null;
            if(args.length > i + 1) {
                nextArg = args[i + 1];
            }

            switch (arg) {
                case "-A":
                    assert nextArg != null;
                    double A = Double.parseDouble(nextArg);
                    triangle.put(Triangle.ComponentType.ANGLE_A, A);
                    break;
                case "-B":
                    assert nextArg != null;
                    double B = Double.parseDouble(nextArg);
                    triangle.put(Triangle.ComponentType.ANGLE_B, B);
                    break;
                case "-C":
                    assert nextArg != null;
                    double C = Double.parseDouble(nextArg);
                    triangle.put(Triangle.ComponentType.ANGLE_C, C);
                    break;
                case "-a":
                    assert nextArg != null;
                    double a = Double.parseDouble(nextArg);
                    triangle.put(Triangle.ComponentType.SIDE_A, a);
                    break;
                case "-b":
                    assert nextArg != null;
                    double b = Double.parseDouble(nextArg);
                    triangle.put(Triangle.ComponentType.SIDE_B, b);
                    break;
                case "-c":
                    assert nextArg != null;
                    double c = Double.parseDouble(nextArg);
                    triangle.put(Triangle.ComponentType.SIDE_C, c);
                    break;
                default:
                    if(!isNumeric(arg)) {
                        log.error("Unexpected argument: " + arg);
                        System.exit(-1);
                    }
                    break;
            }
            i++;
        }


        log.info("Input:  " + triangle);
        triangle = Solver.solve(triangle);
        log.info("Result: " + triangle);
    }

    public static boolean isNumeric(String str) {
        for (char c : str.toCharArray())
        {
            if (!Character.isDigit(c) && c != '.') return false;
        }
        return true;
    }

}
