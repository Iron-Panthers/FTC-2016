package org.firstinspires.ftc.team7316.util.auto;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Const;
import org.firstinspires.ftc.team7316.util.Alliance;
import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Loopable;
import org.firstinspires.ftc.team7316.util.Scheduler;

/**
 * It presses the bacon
 */
public class PressBeacon implements Loopable {

    public static final long wait = 1000;
    public static final long leftUp = 0, leftDown = 0, rightUp = 0, rightDown = 0;

    private Alliance alliance;
    private ColorSensor sensor;
    private Servo left, right;
    private boolean sensorIsOnRight;
    private ElapsedTime pressedTime;

    public PressBeacon(Alliance alliance, ColorSensor sensor, Servo left, Servo right, boolean sensorIsOnRight) {

        this.alliance = alliance;
        this.sensor = sensor;
        this.left = left;
        this.right = right;

        this.sensorIsOnRight = sensorIsOnRight;
        this.pressedTime = new ElapsedTime();
    }

    @Override
    public void init() {
        Scheduler.instance.addTask(new DelayedStart(Constants.COLOR_SENSOR_DELAY, new Runnable() {
            @Override
            public void run() {
                pressedTime.reset();
                if (alliance.isGoodGood(sensor) && sensorIsOnRight) { // If the right sensor is good
                    right.setPosition(Constants.RIGHT_ON);
                } else {
                    left.setPosition(Constants.LEFT_ON);
                }
            }
        }));
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
