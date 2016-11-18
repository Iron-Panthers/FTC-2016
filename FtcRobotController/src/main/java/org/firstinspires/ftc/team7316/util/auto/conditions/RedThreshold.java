package org.firstinspires.ftc.team7316.util.auto.conditions;

import com.qualcomm.robotcore.hardware.ColorSensor;

/**
 * Created by andrew on 11/15/16.
 */
public class RedThreshold implements Conditional {

    private double threshold;
    private ColorSensor sensor;
    private boolean wantedLess;

    public RedThreshold(double threshold, ColorSensor sensor, boolean wantedLess) {
        this.threshold = threshold;
        this.sensor = sensor;
        this.wantedLess = wantedLess;
    }

    @Override
    public boolean shouldRemove() {
        if (wantedLess) {
            return sensor.red() < this.threshold;
        } else {
            return sensor.red() > this.threshold;
        }
    }
}
