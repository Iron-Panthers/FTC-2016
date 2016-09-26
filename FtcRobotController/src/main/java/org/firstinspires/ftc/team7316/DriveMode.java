package org.firstinspires.ftc.team7316;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.team7316.util.AxisWrapper;
import org.firstinspires.ftc.team7316.util.DcMotorWrapper;
import org.firstinspires.ftc.team7316.util.GamepadAxis;
import org.firstinspires.ftc.team7316.util.GamepadWrapper;
import org.firstinspires.ftc.team7316.util.Scheduler;

/**
 * Created by Maxim on 9/26/2016.
 */
@TeleOp(name = "PantherDrive")
public class DriveMode extends OpMode {

    private Hardware hardware;

    private GamepadWrapper gpWrapper;
    private AxisWrapper leftAxis, rightAxis;
    private DcMotorWrapper leftDrive, rightDrive;

    @Override
    public void init() {
        hardware = new Hardware(hardwareMap);

        gpWrapper = new GamepadWrapper(gamepad1);

        leftAxis = new AxisWrapper(GamepadAxis.L_STICK_Y, gpWrapper);
        rightAxis = new AxisWrapper(GamepadAxis.R_STICK_Y, gpWrapper);

        leftDrive = new DcMotorWrapper(hardware.leftDriveMotor, leftAxis);
        rightDrive = new DcMotorWrapper(hardware.rightDriveMotor, rightAxis);

    }

    @Override
    public void loop() {
        Scheduler.instance.loop();
    }
}
