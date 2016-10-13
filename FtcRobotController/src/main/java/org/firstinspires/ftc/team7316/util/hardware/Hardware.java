package org.firstinspires.ftc.team7316.util.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.LightSensor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by andrew on 9/15/16.
 */

//MAKE SURE TO CALL setHardwareMap AT LEAST ONCE

public class Hardware {

    public static Hardware instance = null;

    public static final String tag = "IronPanthers";

    private static final String LEFT_DRIVE_MOTOR_NAME = "mdl";
    private static final String RIGHT_DRIVE_MOTOR_NAME = "mdr";
    private static final String HITTING_MOTOR_NAME = "mh";
    private static final String LEFT_BEACON_SERVO_NAME = "leftServo";
    private static final String RIGHT_BEACON_SERVO_NAME = "rightServo";
    private static final String LEFT_CATCHER_SERVO_NAME = "scl";
    private static final String RIGHT_CATCHER_SERVO_NAME = "scr";
    private static final String LIGHT_SENSOR_NAME = "light";

    public DcMotor leftDriveMotor, rightDriveMotor;
    public DcMotor hittingMotor;
    public Servo leftBeaconServo, rightBeaconServo;
    public Servo leftCatcherServo, rightCatcherServo;
    public LightSensor lightSensor;


    public Hardware (HardwareMap map) {

        leftDriveMotor = map.dcMotor.get(LEFT_DRIVE_MOTOR_NAME);
        leftDriveMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        rightDriveMotor = map.dcMotor.get(RIGHT_DRIVE_MOTOR_NAME);
        rightDriveMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightDriveMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        //leftCatcherServo = map.servo.get(LEFT_CATCHER_SERVO_NAME);
        //rightCatcherServo = map.servo.get(RIGHT_CATCHER_SERVO_NAME);
        //hittingMotor = map.dcMotor.get(HITTING_MOTOR_NAME);
        //hittingMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // leftBeaconServo = map.servo.get(LEFT_BEACON_SERVO_NAME);
        // rightBeaconServo = map.servo.get(RIGHT_BEACON_SERVO_NAME);

        lightSensor = map.lightSensor.get(LIGHT_SENSOR_NAME);
    }

    public static void setHardwareMap(HardwareMap map) {
        instance = new Hardware(map);
    }
}
