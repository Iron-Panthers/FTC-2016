package org.firstinspires.ftc.team7316.util.hardware;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.team7316.util.Scheduler;

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
    private static final String BEACON_WHEEL_SERVO_NAME = "boss";
    private static final String CAP_BALL_WHACKER_MOTOR_NAME = "wkm";
    private static final String CAP_BALL_WHACKER_LEFT = "wkl";
    private static final String CAP_BALL_WHACKER_RIGHT = "wkr";

    public DcMotor leftDriveMotor;
    public DcMotor rightDriveMotor;
    public DcMotor catapultMotor;
    public DcMotor intakeMotor, capBallWhackerMotor;
    public Servo leftBeaconServo, rightBeaconServo, intakeUpServo;
    public Servo beaconWheelServo;
    public OpticalDistanceSensor catapultSensor, lightSensorFront, lightSensorBack;
    public GyroSensor gyroSensor;
    //public DcMotor capBallMotor;
    public ColorSensor colorSensor;
    public TouchSensor touchSensor, whackerLeft, whackerRight;
    public SharpIRSensor frontSideInfaredSensor, backSideInfaredSensor;

    public WhackerWrapper whackerWrapper;

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

        catapultMotor = map.dcMotor.get(CATAPULT_MOTOR_NAME);
        catapultMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        catapultSensor = map.opticalDistanceSensor.get(CATAPULT_SENSOR_NAME);

        intakeMotor = map.dcMotor.get(INTAKE_MOTOR_NAME);
        intakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        capBallWhackerMotor = map.dcMotor.get(CAP_BALL_WHACKER_MOTOR_NAME);
        capBallWhackerMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        leftBeaconServo = map.servo.get(LEFT_BEACON_SERVO_NAME);
        rightBeaconServo = map.servo.get(RIGHT_BEACON_SERVO_NAME);

        lightSensorFront = map.opticalDistanceSensor.get(LIGHT_SENSOR_NAME_LEFT);
        lightSensorBack = map.opticalDistanceSensor.get(LIGHT_SENSOR_NAME_RIGHT);
        gyroSensor = map.gyroSensor.get(GYRO_SENSOR_NAME);
        colorSensor = map.colorSensor.get(COLOR_SENSOR_NAME);
        //distanceSensor = map.ultrasonicSensor.get(DISTANCE_SENSOR_NAME);

        intakeUpServo = map.servo.get(INTAKE_UP_SERVO_NAME);

        touchSensor = map.touchSensor.get(TOUCH_SENSOR_NAME);

        whackerLeft = new InvertedTouchSensor(map.touchSensor.get(CAP_BALL_WHACKER_LEFT));
        whackerRight = map.touchSensor.get(CAP_BALL_WHACKER_RIGHT);

        whackerWrapper = new WhackerWrapper(capBallWhackerMotor, whackerLeft, whackerRight);

        //capBallMotor = map.dcMotor.get(CAP_BALL_MOTOR_NAME);

        frontSideInfaredSensor = new SharpIRSensor(map.analogInput.get(FRONT_SIDE_IR_NAME), 34.301, 0.48347, -13.799);
        backSideInfaredSensor = new SharpIRSensor(map.analogInput.get(BACK_SIDE_IR_NAME), 35.286, 0.19009, -15.371);

        Scheduler.instance.addTask(frontSideInfaredSensor);
        Scheduler.instance.addTask(backSideInfaredSensor);

        beaconWheelServo = map.servo.get(BEACON_WHEEL_SERVO_NAME);
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

    public static void memel0rd() {
        log("memelord", "mgee");
    }
}
