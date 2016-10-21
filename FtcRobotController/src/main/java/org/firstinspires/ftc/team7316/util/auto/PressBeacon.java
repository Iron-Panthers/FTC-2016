package org.firstinspires.ftc.team7316.util.auto;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.team7316.util.Alliance;
import org.firstinspires.ftc.team7316.util.Loopable;

/**
 * Created by Maxim on 10/18/2016.
 */
public class PressBeacon implements Loopable {

    public static final long wait = 1000;
    public static final long leftUp = 0, leftDown = 0, rightUp = 0, rightDown = 0;

    private Alliance alliance;
    private ColorSensor sensor;
    private Servo left, right;
    private boolean sensorIsOnRight;

    public PressBeacon(Alliance alliance, ColorSensor sensor, Servo left, Servo right, boolean sensorIsOnRight) {

        this.alliance = alliance;
        this.sensor = sensor;
        this.left = left;
        this.right = right;

        this.sensorIsOnRight = sensorIsOnRight;
    }

    @Override
    public void init() {
        if (alliance.isGoodGood(sensor) && sensorIsOnRight) {

        }
    }

    @Override
    public void loop() {

    }

    @Override
    public boolean shouldRemove() {
        return false;
    }

    @Override
    public void terminate() {

    }
}
