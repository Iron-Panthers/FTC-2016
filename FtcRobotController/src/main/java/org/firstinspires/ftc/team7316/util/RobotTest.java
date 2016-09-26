package org.firstinspires.ftc.team7316.util;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.team7316.Hardware;

/**
 * Created by andrew on 9/15/16.
 */

//@TeleOp(name = "RobotTest", group = "IronPanthers")
public class RobotTest extends OpMode {

    private Hardware hardware;
    private GamepadWrapper gp1;

    private DcMotorWrapper mLWrapper;
    private DcMotorWrapper mRWrapper;
    private ServoWrapper aButtonServoWrapper;
    private ServoWrapper triggerServoWrapper;

    @Override
    public void init() {
        hardware = new Hardware(hardwareMap);
        gp1 = new GamepadWrapper(gamepad1);

        //mLWrapper = new DcMotorWrapper(hardware.leftMotor, gp1.left_stick_y);
        mRWrapper = new DcMotorWrapper(hardware.rightDriveMotor, gp1.right_stick_y);

        aButtonServoWrapper = new ServoWrapper(hardware.leftBeaconServo, gp1.aButtonWrapper);
        triggerServoWrapper = new ServoWrapper(hardware.rightBeaconServo, gp1.leftTriggerWrapper);
    }

    @Override
    public void loop() {
        Scheduler.instance.loop();

        hardware.leftDriveMotor.setPower(1.0);
    }
}
