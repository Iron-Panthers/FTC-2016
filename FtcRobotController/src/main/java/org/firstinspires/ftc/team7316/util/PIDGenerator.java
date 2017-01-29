package org.firstinspires.ftc.team7316.util;

/**
 * A class for PID
 */
public class PIDGenerator {

    private final double p, i, d;

    private double previous, sum;

    public PIDGenerator(double p, double i, double d) {
        this.p = p;
        this.i = i;
        this.d = d;
    }

    public double pushError(double error) {
        sum += error;
        double delta = previous - error;
        double output = p*error + i*sum + d*delta;
        previous = error;
        return output;
    }

}
