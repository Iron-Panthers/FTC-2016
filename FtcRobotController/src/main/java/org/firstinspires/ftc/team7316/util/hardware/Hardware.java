package org.firstinspires.ftc.team7316.util.hardware;

import android.graphics.Color;
import android.webkit.CookieManager;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.LightSensor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by andrew on 9/15/16.
 */

//MAKE SURE TO CALL setHardwareMap AT LEAST ONCE

/*
    toggle a b needs to always disable if its the wrong button √
    need to merge into master √ (sort of, still need to do auto merge)
    need to reduce intake speed √
    need to tune line follow
    need to test auto as one full command sequence
    need to zip tie down the wire across the intake ramp √
    need to zip tie down the wire to up intake servo √
    need to write up intake servo code √
    motor and joystick deadzones
*/

public class Hardware {

    public static Hardware instance = null;

    public static final String tag = "IronPanthers";

    private static Telemetry telemetry;

    private static final String LEFT_DRIVE_MOTOR_NAME = "mdl";
    private static final String RIGHT_DRIVE_MOTOR_NAME = "mdr";
    private static final String INTAKE_MOTOR_NAME = "im";
    private static final String CATAPULT_MOTOR_NAME = "cm";
    private static final String CAP_BALL_MOTOR_NAME = "cpm";
    private static final String LEFT_BEACON_SERVO_NAME = "leftServo";
    private static final String RIGHT_BEACON_SERVO_NAME = "rightServo";
    private static final String CATAPULT_SENSOR_NAME = "cat";
    private static final String GYRO_SENSOR_NAME = "gyro";
    private static final String COLOR_SENSOR_NAME = "color";
    private static final String LIGHT_SENSOR_NAME_RIGHT = "lir";
    private static final String LIGHT_SENSOR_NAME_LEFT = "lil";
    private static final String TOUCH_SENSOR_NAME = "touch";
    private static final String INTAKE_UP_SERVO_NAME = "inUp";
    private static final String FRONT_SIDE_IR_NAME = "fi";
    private static final String BACK_SIDE_IR_NAME = "bi";

    public DcMotor leftDriveMotor;
    public DcMotor rightDriveMotor; //boosted motor
    public DcMotor catapultMotor;
    public DcMotor intakeMotor;
    public Servo leftBeaconServo, rightBeaconServo, intakeUpServo;
    public OpticalDistanceSensor catapultSensor, lightSensorLeft, lightSensorRight;
    public GyroSensor gyroSensor;
    public DcMotor capBallMotor;
    public ColorSensor colorSensor;
    public TouchSensor touchSensor;
    public SharpIRSensor frontSideInfaredSensor, backSideInfaredSensor;

    public double jankDelta = 0;
    public double jankSum = 0;

    public Hardware (HardwareMap map) {

        frontSideInfaredSensor = new SharpIRSensor(map.analogInput.get(FRONT_SIDE_IR_NAME));
        backSideInfaredSensor = new SharpIRSensor(map.analogInput.get(BACK_SIDE_IR_NAME));
    }

    public static void setHardwareMap(HardwareMap map) {
        instance = new Hardware(map);
    }

    public static void setTelemetry(Telemetry telemetry) {
        Hardware.telemetry = telemetry;
    }

    public static void log(String caption, Object value) {
        if (telemetry != null) {
            telemetry.addData(caption, value);
        }
    }
}
