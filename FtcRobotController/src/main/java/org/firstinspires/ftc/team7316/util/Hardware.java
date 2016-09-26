package org.firstinspires.ftc.team7316.util;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by andrew on 9/15/16.
 */
public class Hardware {

    public static final String tag = "IronPanthers";

    private static final String leftMotorName = "mL";
    private static final String rightMotorName = "mR";
    private static final String aButtonServoName = "s1";
    private static final String leftTriggerServoName = "s2";

    public DcMotor leftMotor;
    public DcMotor rightMotor;
    public Servo aButtonServo;
    public Servo triggerServo;

    public Hardware (HardwareMap map) {
        leftMotor = map.dcMotor.get(leftMotorName);
        leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        rightMotor = map.dcMotor.get(rightMotorName);
        rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        aButtonServo = map.servo.get(aButtonServoName);
        triggerServo = map.servo.get(leftTriggerServoName);
    }
}
