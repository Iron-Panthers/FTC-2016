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

        leftDriveMotor = map.dcMotor.get(LEFT_DRIVE_MOTOR_NAME);
        leftDriveMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftDriveMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        DcMotor rightMotor = map.dcMotor.get(RIGHT_DRIVE_MOTOR_NAME);
        rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightDriveMotor = new BoostedMotor(rightMotor, 1.05);
        /*rightDriveMotor = map.dcMotor.get(RIGHT_DRIVE_MOTOR_NAME);
        rightDriveMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);*/

        //leftCatcherServo = map.servo.get(LEFT_CATCHER_SERVO_NAME);
        //rightCatcherServo = map.servo.get(RIGHT_CATCHER_SERVO_NAME);

        catapultMotor = map.dcMotor.get(CATAPULT_MOTOR_NAME);
        catapultMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        catapultSensor = map.opticalDistanceSensor.get(CATAPULT_SENSOR_NAME);

        intakeMotor = map.dcMotor.get(INTAKE_MOTOR_NAME);
        intakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        leftBeaconServo = map.servo.get(LEFT_BEACON_SERVO_NAME);
        rightBeaconServo = map.servo.get(RIGHT_BEACON_SERVO_NAME);

        lightSensorLeft = map.opticalDistanceSensor.get(LIGHT_SENSOR_NAME_LEFT);
        lightSensorRight = map.opticalDistanceSensor.get(LIGHT_SENSOR_NAME_RIGHT);
        gyroSensor = map.gyroSensor.get(GYRO_SENSOR_NAME);
        colorSensor = map.colorSensor.get(COLOR_SENSOR_NAME);
        //distanceSensor = map.ultrasonicSensor.get(DISTANCE_SENSOR_NAME);

        intakeUpServo = map.servo.get(INTAKE_UP_SERVO_NAME);

        touchSensor = map.touchSensor.get(TOUCH_SENSOR_NAME);

        capBallMotor = map.dcMotor.get(CAP_BALL_MOTOR_NAME);

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
