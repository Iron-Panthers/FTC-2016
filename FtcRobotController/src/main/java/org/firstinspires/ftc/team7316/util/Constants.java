package org.firstinspires.ftc.team7316.util;

/**
 * Created by wayne on 10/21/2016.
 */
public class Constants {

    public static final double LEFT_OFF = 0.12, RIGHT_OFF = 0.48, LEFT_ON = 0.35, RIGHT_ON = 0.25;
    public static final double INTAKE_SERVO_LOCKED = 0.48, INTAKE_SERVO_RELEASE = 0, INTAKE_SERVO_DONT_STORE = 0.6;
    public static final double PRESSER_SERVO_TRAVEL_TIME = 0.5; // seconds
    public static final double COLOR_SENSOR_DELAY = 0.1; // seconds
    public static final double COLOR_DIFFERENCE = 2;
    public static final float JOYSTICK_DRIVE_DEADZONE = 0.03f;
    public static final float DRIVER_MOTOR_DEADZONE = 0.16f;
    public static final int ENCODER_TICK_PER_REV = 1120;
    public static final double DISTANCE_PER_REV = 4*Math.PI;
    public static final double INTAKE_IN_SPEED = 0.7;
    public static final int CAP_BALL_TOP_LIMIT = 18000;
    public static final double WHEEL_SERVO_RELEASE = 0.5;
    public static final double CAP_BALL_SPEED = 1;
    public static final double CAP_THRESHOLD = 0.1;
    public static final double WANTED_LIGHT = 0.23; //FIX THESE NUMBERS WITH TESTING
    public static final double MIN_LIGHT = 0.04;
    public static final double MAX_LIGHT = 0.4;

    public static double distanceToTicks(double dist) {
        double inches = dist*12;
        double revs = inches/DISTANCE_PER_REV;
        double ticks = revs*ENCODER_TICK_PER_REV;
        return ticks;
    }

}
