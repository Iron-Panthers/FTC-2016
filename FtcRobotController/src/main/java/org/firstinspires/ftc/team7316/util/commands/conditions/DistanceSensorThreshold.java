package org.firstinspires.ftc.team7316.util.commands.conditions;

import com.qualcomm.robotcore.hardware.UltrasonicSensor;

/**
 * Created by andrew on 11/2/16.
 */
public class DistanceSensorThreshold implements Conditional {

    private UltrasonicSensor distanceSensor;
    private double threshold;
    private boolean wantedLess;

    public DistanceSensorThreshold(UltrasonicSensor distanceSensor, double thresh, boolean wantedLess) { //if wanted less is true, then it will return true if distance is less than thresh
        this.distanceSensor = distanceSensor;
        this.threshold = thresh;
        this.wantedLess = wantedLess;
    }

    @Override
    public boolean state() {
        if (this.wantedLess) {
            return distanceSensor.getUltrasonicLevel() < this.threshold;
        } else {
            return distanceSensor.getUltrasonicLevel() > this.threshold;
        }
    }

}
