package org.firstinspires.ftc.team7316.util.hardware;

import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.team7316.util.Loopable;

/**
 * Created by andrew on 10/11/16.
 */

import org.firstinspires.ftc.team7316.Buffer;

public class ColorWrapper implements Loopable {

    private ColorSensor sensor;
    private final static int bufferSize = 60;
    private Buffer redSum;
    private Buffer greenSum;
    private Buffer blueSum;

    public ColorWrapper(ColorSensor sensor) {
        this.sensor = sensor;
    }

    @Override
    public void init() {
        redSum = new Buffer(bufferSize);
        greenSum = new Buffer(bufferSize);
        blueSum = new Buffer(bufferSize);
    }

    @Override
    public void loop() {
        redSum.pushValue(this.sensor.red());
        greenSum.pushValue(this.sensor.green());
        blueSum.pushValue(this.sensor.blue());
    }

    @Override
    public boolean shouldRemove() {
        return false;
    }

    @Override
    public void terminate() {

    }

    public double sumR() {
        return redSum.sum;
    }

    public double sumG() {
        return greenSum.sum;
    }

    public double sumB() {
        return blueSum.sum;
    }
}
