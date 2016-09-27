package org.firstinspires.ftc.team7316;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.team7316.util.input.AxisWrapper;
import org.firstinspires.ftc.team7316.util.hardware.DcMotorWrapper;
import org.firstinspires.ftc.team7316.util.input.GamepadAxis;
import org.firstinspires.ftc.team7316.util.input.GamepadWrapper;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.hardware.ServoWrapper;

/**
 * Created by Maxim on 9/26/2016.
 */
@TeleOp(name = "PantherDrive")
public class DriveMode extends OpMode {

    private Hardware hardware;

    private GamepadWrapper gpWrapper;
    private DcMotorWrapper leftDrive, rightDrive;

    @Override
    public void init() {
        hardware = new Hardware(hardwareMap);

        gpWrapper = new GamepadWrapper(gamepad1);

        leftDrive = new DcMotorWrapper(hardware.leftDriveMotor, gpWrapper.right_axis_y, false);
        rightDrive = new DcMotorWrapper(hardware.rightDriveMotor, gpWrapper.left_axis_y, true);

    }

    @Override
    public void loop() {
        Scheduler.instance.loop();
    }
}
