package org.firstinspires.ftc.team7316.util;

/**
 * Created by wayne on 10/21/2016.
 */
public class Constants {

    public static final double LEFT_OFF = 0.12, RIGHT_OFF = 0.48, LEFT_ON = 0.35, RIGHT_ON = 0.25;
    public static final double INTAKE_SERVO_LOCKED = 0.48, INTAKE_SERVO_RELEASE = 0, INTAKE_SERVO_DONT_STORE = 0.6; // TODO: calibrate values
    public static final double PRESSER_SERVO_TRAVEL_TIME = 1; // seconds
    public static final double COLOR_SENSOR_DELAY = 0.1; // seconds
    public static final double COLOR_DIFFERENCE = 2;
    public static final float JOYSTICK_DRIVE_DEADZONE = 0.07f;
    public static final float DRIVER_MOTOR_DEADZONE = 0.15f;
    public static final int ENCODER_TICK_PER_REV = 1120;
    public static final double DISTANCE_PER_REV = 4*Math.PI;
    public static final double INTAKE_IN_SPEED = 0.4;
    public static final int CAP_BALL_TOP_LIMIT = 100000;
    public static final double CAP_BALL_SPEED = 1;

    public static double distanceToTicks(double dist) {
        double inches = dist*12;
        double revs = inches/DISTANCE_PER_REV;
        double ticks = revs*ENCODER_TICK_PER_REV;
        return ticks;
    }

}
