package org.firstinspires.ftc.team7316.modes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.LightSensor;

import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.hardware.DcMotorToggleWrapper;
import org.firstinspires.ftc.team7316.util.hardware.DcMotorWrapper;
import org.firstinspires.ftc.team7316.util.hardware.Hardware;
import org.firstinspires.ftc.team7316.util.hardware.ServoWrapper;
import org.firstinspires.ftc.team7316.util.input.GamepadButton;
import org.firstinspires.ftc.team7316.util.input.GamepadWrapper;
import org.firstinspires.ftc.team7316.util.input.ToggleButtonWrapper;

/**
 * Created by Maxim on 9/26/2016.
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

        rightPusher = new ServoWrapper(Hardware.instance.rightBeaconServo, gpWrapper.right_bumper);
        leftPusher = new ServoWrapper(Hardware.instance.leftBeaconServo, gpWrapper.left_bumper);
    }

    @Override
    public void loop() {
        Scheduler.instance.loop();
    }
}
