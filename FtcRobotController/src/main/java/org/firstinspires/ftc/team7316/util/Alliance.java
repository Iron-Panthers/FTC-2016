package org.firstinspires.ftc.team7316.util;

import com.qualcomm.robotcore.hardware.ColorSensor;

/**
 * Created by Maxim on 10/18/2016.
 */
public enum Alliance {
    RED(30), BLUE(17);

    public final int threshold;

    Alliance(int threshold) {
        this.threshold = threshold;
    }

    /**
     * Check if good good
     * @param sensor the sensor to check if good good
     * @return is good good or not is good good
     */
    public boolean isGoodGood(ColorSensor sensor) {
        switch (this) {
            case RED:
                return sensor.red() >= threshold;
            case BLUE:
                return sensor.blue() >= threshold;
        }
        throw new IllegalArgumentException("Something wrong happened in enum Alliance even though nothing wrong should have happened");
    }
}
