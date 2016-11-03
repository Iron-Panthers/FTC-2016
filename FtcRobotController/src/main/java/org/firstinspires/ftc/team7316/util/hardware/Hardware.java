package org.firstinspires.ftc.team7316.util.hardware;

import android.graphics.Color;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.LightSensor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by andrew on 9/15/16.
 */

//MAKE SURE TO CALL setHardwareMap AT LEAST ONCE

public class Hardware {

    public static Hardware instance = null;

    public static final String tag = "IronPanthers";

    private static Telemetry telemetry;

    private static final String LEFT_DRIVE_MOTOR_NAME = "mdl";
    private static final String RIGHT_DRIVE_MOTOR_NAME = "mdr";
    private static final String HITTING_MOTOR_NAME = "mh";
    private static final String LEFT_BEACON_SERVO_NAME = "leftServo";
    private static final String RIGHT_BEACON_SERVO_NAME = "rightServo";
    private static final String LEFT_CATCHER_SERVO_NAME = "scl";
    private static final String RIGHT_CATCHER_SERVO_NAME = "scr";
    private static final String LIGHT_SENSOR_NAME = "light";
    private static final String GYRO_SENSOR_NAME = "gyro";
    private static final String COLOR_SENSOR_NAME = "color";
    private static final String DISTANCE_SENSOR_NAME = "dist";

    public DcMotor leftDriveMotor, rightDriveMotor;
    public DcMotor hittingMotor;
    public Servo leftBeaconServo, rightBeaconServo;
    public Servo leftCatcherServo, rightCatcherServo;
    public LightSensor lightSensor;
    public GyroSensor gyroSensor;
    public ColorSensor colorSensor;
    public OpticalDistanceSensor distanceSensor;

    public double jankDelta = 0;
    public double jankSum = 0;

    public Hardware (HardwareMap map) {

        leftDriveMotor = map.dcMotor.get(LEFT_DRIVE_MOTOR_NAME);
        leftDriveMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftDriveMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        rightDriveMotor = map.dcMotor.get(RIGHT_DRIVE_MOTOR_NAME);
        rightDriveMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        //leftCatcherServo = map.servo.get(LEFT_CATCHER_SERVO_NAME);
        //rightCatcherServo = map.servo.get(RIGHT_CATCHER_SERVO_NAME);
        //hittingMotor = map.dcMotor.get(HITTING_MOTOR_NAME);
        //hittingMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        leftBeaconServo = map.servo.get(LEFT_BEACON_SERVO_NAME);
        rightBeaconServo = map.servo.get(RIGHT_BEACON_SERVO_NAME);

        lightSensor = map.lightSensor.get(LIGHT_SENSOR_NAME);
        gyroSensor = map.gyroSensor.get(GYRO_SENSOR_NAME);
        colorSensor = map.colorSensor.get(COLOR_SENSOR_NAME);
        distanceSensor = map.opticalDistanceSensor.get(DISTANCE_SENSOR_NAME);
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
