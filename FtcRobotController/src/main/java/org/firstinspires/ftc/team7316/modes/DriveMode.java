package org.firstinspires.ftc.team7316.modes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.LightSensor;

import org.firstinspires.ftc.robotcore.external.Const;
import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.hardware.DcMotorToggleWrapper;
import org.firstinspires.ftc.team7316.util.hardware.DcMotorWrapper;
import org.firstinspires.ftc.team7316.util.hardware.Hardware;
import org.firstinspires.ftc.team7316.util.hardware.ServoWrapper;
import org.firstinspires.ftc.team7316.util.input.GamepadButton;
import org.firstinspires.ftc.team7316.util.input.GamepadWrapper;
import org.firstinspires.ftc.team7316.util.input.ToggleButtonWrapper;

/*
gamepad1:
-left stick y = left motor power
-right stick y = right motor power

gamepad2:
-left bumper: left beacon servo
-right bumper: right beacon servo
-right trigger (with threshold) = catapult next movement in cycle (either go to sensor or go past sensor)
-left trigger (with threshold) = move catapult backwards
-a button = run intake inward (toggle)
-b button = run intake outward (toggle)
 */
@TeleOp(name = "PantherDrive")
public class DriveMode extends OpMode {

    private GamepadWrapper gpWrapper;
    private DcMotorWrapper leftDrive, rightDrive;
    private DcMotorToggleWrapper intakeDrive;
    private DcMotorWrapper catapultDrive;

    private ServoWrapper leftPusher, rightPusher;


    @Override
    public void init() {
        Scheduler.instance.clear();

        gpWrapper = new GamepadWrapper(gamepad1);
        Hardware.setHardwareMap(hardwareMap);
        Hardware.setTelemetry(telemetry);

        leftDrive = new DcMotorWrapper(Hardware.instance.leftDriveMotor, gpWrapper.left_axis_y);
        rightDrive = new DcMotorWrapper(Hardware.instance.rightDriveMotor, gpWrapper.right_axis_y);

        ToggleButtonWrapper toggleAButton = new ToggleButtonWrapper(GamepadButton.A_BUTTON, gpWrapper);
        intakeDrive = new DcMotorToggleWrapper(Hardware.instance.intakeMotor, 0, 1.0, toggleAButton);

        catapultDrive = new DcMotorWrapper(Hardware.instance.catapultMotor, gpWrapper.r_trigger);

        rightPusher = new ServoWrapper(Hardware.instance.rightBeaconServo, gpWrapper.right_bumper, Constants.RIGHT_ON, Constants.RIGHT_OFF);
        leftPusher = new ServoWrapper(Hardware.instance.leftBeaconServo, gpWrapper.left_bumper, Constants.LEFT_ON, Constants.LEFT_OFF);
    }

    @Override
    public void loop() {
        Scheduler.instance.loop();
    }
}
