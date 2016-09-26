package org.firstinspires.ftc.team7316.util;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

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
        mRWrapper = new DcMotorWrapper(hardware.rightMotor, gp1.right_stick_y);

        aButtonServoWrapper = new ServoWrapper(hardware.aButtonServo, gp1.aButtonWrapper);
        triggerServoWrapper = new ServoWrapper(hardware.triggerServo, gp1.leftTriggerWrapper);
    }

    @Override
    public void loop() {
        Scheduler.instance.loop();

        hardware.leftMotor.setPower(1.0);
    }
}
