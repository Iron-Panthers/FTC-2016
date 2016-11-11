package org.firstinspires.ftc.team7316.modes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.LightSensor;

import org.firstinspires.ftc.robotcore.external.Const;
import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.hardware.DcMotorThreeStateWrapper;
import org.firstinspires.ftc.team7316.util.hardware.DcMotorToggleWrapper;
import org.firstinspires.ftc.team7316.util.hardware.DcMotorWrapper;
import org.firstinspires.ftc.team7316.util.hardware.Hardware;
import org.firstinspires.ftc.team7316.util.hardware.ServoWrapper;
import org.firstinspires.ftc.team7316.util.input.GamepadButton;
import org.firstinspires.ftc.team7316.util.input.GamepadWrapper;
import org.firstinspires.ftc.team7316.util.input.ToggleButtonWrapper;
import org.firstinspires.ftc.team7316.util.input.TwoButtonToggleWrapper;

/*
gamepad1:
-left stick y = left motor power √
-right stick y = right motor power √

gamepad2:
-left bumper: left beacon servo √
-right bumper: right beacon servo √
-right trigger (with threshold) = catapult next movement in cycle (either go to sensor or go past sensor)
-left trigger (with threshold) = move catapult backwards
-a button = run intake inward (toggle) √
-b button = run intake outward (toggle) √
 */
@TeleOp(name = "PantherDrive")
public class DriveMode extends OpMode {

    private GamepadWrapper gpWrapperDriver;
    private GamepadWrapper gpWrapperNotDriver;

    private DcMotorWrapper leftDrive, rightDrive;
    private DcMotorThreeStateWrapper intakeDrive;
    private DcMotorWrapper catapultDrive;

    private ServoWrapper leftPusher, rightPusher;


    @Override
    public void init() {
        Scheduler.instance.clear();

        gpWrapperDriver = new GamepadWrapper(gamepad1);
        gpWrapperNotDriver = new GamepadWrapper(gamepad2);

        Hardware.setHardwareMap(hardwareMap);
        Hardware.setTelemetry(telemetry);

        leftDrive = new DcMotorWrapper(Hardware.instance.leftDriveMotor, gpWrapperDriver.left_axis_y);
        rightDrive = new DcMotorWrapper(Hardware.instance.rightDriveMotor, gpWrapperDriver.right_axis_y);

        TwoButtonToggleWrapper aAndBToggle = new TwoButtonToggleWrapper(gpWrapperNotDriver.a_button, gpWrapperNotDriver.b_button);
        intakeDrive = new DcMotorThreeStateWrapper(Hardware.instance.intakeMotor, 1.0, 0, -1.0, aAndBToggle);

        catapultDrive = new DcMotorWrapper(Hardware.instance.catapultMotor, gpWrapperDriver.r_trigger);

        rightPusher = new ServoWrapper(Hardware.instance.rightBeaconServo, gpWrapperNotDriver.right_bumper, Constants.RIGHT_ON, Constants.RIGHT_OFF);
        leftPusher = new ServoWrapper(Hardware.instance.leftBeaconServo, gpWrapperNotDriver.left_bumper, Constants.LEFT_ON, Constants.LEFT_OFF);
    }

    @Override
    public void loop() {
        Scheduler.instance.loop();
    }
}
