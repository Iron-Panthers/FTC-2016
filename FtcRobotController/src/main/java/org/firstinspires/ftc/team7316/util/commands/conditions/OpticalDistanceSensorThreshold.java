package org.firstinspires.ftc.team7316.util.commands.conditions;

import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;

/**
 * Created by andrew on 11/8/16.
 */
public class OpticalDistanceSensorThreshold implements Conditional {

    private OpticalDistanceSensor sensor;
    private double threshold;
    private boolean wantedLess;

    public OpticalDistanceSensorThreshold(OpticalDistanceSensor sensor, double thresh, boolean wantedLess) { //if wanted less is true, then it will return true if distance is less than thresh
        this.sensor = sensor;
        this.threshold = thresh;
        this.wantedLess = wantedLess;
    }

    @Override
    public boolean state() {
        if (this.wantedLess) {
            return sensor.getLightDetected() < this.threshold;
        } else {
            return sensor.getLightDetected() > this.threshold;
        }
    }
}
