package org.firstinspires.ftc.team7316;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.hardware.DcMotorWrapper;
import org.firstinspires.ftc.team7316.util.hardware.Hardware;
import org.firstinspires.ftc.team7316.util.hardware.ServoWrapper;
import org.firstinspires.ftc.team7316.util.input.GamepadWrapper;

/**
 * Created by andrew on 11/1/16.
 */
@TeleOp(name = "EncoderDrive")
public class EncoderTest extends OpMode {

    private GamepadWrapper gpWrapper;
    private DcMotorWrapper leftDrive, rightDrive;

    @Override
    public void init() {
        Scheduler.instance.clear();

        Hardware.setHardwareMap(hardwareMap);

        gpWrapper = new GamepadWrapper(gamepad1);

        leftDrive = new DcMotorWrapper(Hardware.instance.leftDriveMotor, gpWrapper.left_axis_y);
        rightDrive = new DcMotorWrapper(Hardware.instance.rightDriveMotor, gpWrapper.right_axis_y);
    }

    @Override
    public void loop() {
        Scheduler.instance.loop();
        Hardware.log(Hardware.tag, "Left Motor: " + Hardware.instance.leftDriveMotor.getCurrentPosition());
        Hardware.log(Hardware.tag, "Right Motor: " + Hardware.instance.rightDriveMotor.getCurrentPosition());
    }
}
