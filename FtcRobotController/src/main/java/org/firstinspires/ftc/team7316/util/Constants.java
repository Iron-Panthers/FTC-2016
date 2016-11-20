package org.firstinspires.ftc.team7316.util;

/**
 * Created by wayne on 10/21/2016.
 */
public class Constants {

    public static final double LEFT_OFF = 0.12, RIGHT_OFF = 0.48, LEFT_ON = 0.35, RIGHT_ON = 0.25;
    public static final double INTAKE_SERVO_LOCKED = 0.3, INTAKE_SERVO_RELEASE = 0; // TODO: calibrate values
    public static final double PRESSER_SERVO_TRAVEL_TIME = 32; // seconds
    public static final double COLOR_SENSOR_DELAY = 0.5; // seconds
    public static final double COLOR_DIFFERENCE = 2;
    public static final float JOYSTICK_DRIVE_DEADZONE = 0.07f;
    public static final float DRIVER_MOTOR_DEADZONE = 0.1f;
    public static final int ENCODER_TICK_PER_REV = 1440;
    public static final double DISTANCE_PER_REV = 4*Math.PI;

    public static double distanceToTicks(double dist) {
        double inches = dist*12;
        double revs = dist/DISTANCE_PER_REV;
        double ticks = revs*ENCODER_TICK_PER_REV;
        return ticks;
    }

}
