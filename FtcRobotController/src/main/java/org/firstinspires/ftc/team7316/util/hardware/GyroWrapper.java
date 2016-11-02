package org.firstinspires.ftc.team7316.util.hardware;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.GyroSensor;

import org.firstinspires.ftc.team7316.util.Loopable;

/**
 * Created by andrew on 10/11/16.
 */
public class GyroWrapper implements Loopable {

    private GyroSensor sensor;
    private static final double threshold = 0;
    private double sumX = 0;
    private double sumY = 0;
    private double sumZ = 0;

    public GyroWrapper(GyroSensor gyro) {
        sensor = gyro;
    }

    @Override
    public void init() {
        sumX = 0;
        sumY = 0;
        sumZ = 0;
    }

    @Override
    public void loop() {
        this.sumX += this.currentX();
        this.sumY += this.currentY();
        this.sumZ += this.currentZ();
    }

    @Override
    public boolean shouldRemove() {
        return false;
    }

    @Override
    public void terminate() {

    }

    public double currentX() {
        if (Math.abs(this.sensor.rawX()) < this.threshold) {
            return 0;
        } else {
            return this.sensor.rawX();
        }
    }

    public double currentY() {
        if (Math.abs(this.sensor.rawY()) < this.threshold) {
            return 0;
        } else {
            return this.sensor.rawY();
        }
    }

    public double currentZ() {
        if (Math.abs(this.sensor.rawZ()) < this.threshold) {
            return 0;
        } else {
            return this.sensor.rawZ();
        }
    }

    public double sumX() {
        return this.sumX;
    }

    public double sumY() {
        return this.sumY;
    }

    public double sumZ() {
        return this.sumZ;
    }

    public double getHeading() {
        return sensor.getHeading();
    }

    public void calibrate() {
        sensor.calibrate();
    }
}
