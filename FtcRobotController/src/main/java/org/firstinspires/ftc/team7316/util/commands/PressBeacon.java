package org.firstinspires.ftc.team7316.util.commands;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.team7316.util.Alliance;
import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Loopable;
import org.firstinspires.ftc.team7316.util.Scheduler;

/**
 * It presses the bacon
 */
public class PressBeacon implements Loopable {

    public static final long wait = 1000;

    private Alliance alliance;
    private ColorSensor sensor;
    private Servo left, right;
    private ElapsedTime pressedTime;

    public PressBeacon(Alliance alliance, ColorSensor sensor, Servo left, Servo right) { //sensor should be on the left

        this.alliance = alliance;
        this.sensor = sensor;
        this.left = left;
        this.right = right;
        this.pressedTime = new ElapsedTime();
    }

    @Override
    public void init() {
        if (alliance.shouldPressLeftServo(sensor)) { // If the left servo should be pressed
            left.setPosition(Constants.LEFT_ON);
        } else {
            right.setPosition(Constants.RIGHT_ON);
        }
        this.pressedTime.reset();
    }

    @Override
    public void loop() {

    }

    @Override
    public boolean shouldRemove() {
        return pressedTime.seconds() >= Constants.PRESSER_SERVO_TRAVEL_TIME;
    }

    @Override
    public void terminate() {
        right.setPosition(Constants.RIGHT_OFF);
        left.setPosition(Constants.LEFT_OFF);
    }
}
