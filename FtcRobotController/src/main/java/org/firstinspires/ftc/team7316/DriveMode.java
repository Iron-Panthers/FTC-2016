package org.firstinspires.ftc.team7316;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.LightSensor;

import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.hardware.DcMotorWrapper;
import org.firstinspires.ftc.team7316.util.hardware.Hardware;
import org.firstinspires.ftc.team7316.util.hardware.ServoWrapper;
import org.firstinspires.ftc.team7316.util.input.GamepadWrapper;

/**
 * Created by Maxim on 9/26/2016.
 */
@TeleOp(name = "PantherDrive")
public class DriveMode extends OpMode {

    private GamepadWrapper gpWrapper;
    private DcMotorWrapper leftDrive, rightDrive;

    private ServoWrapper leftPusher, rightPusher;


    @Override
    public void init() {
        Scheduler.instance.clear();

        Hardware.setHardwareMap(hardwareMap);
        //Hardware.instance.lightSensor.enableLed(true);

        gpWrapper = new GamepadWrapper(gamepad1);

        //leftDrive = new DcMotorWrapper(Hardware.instance.leftDriveMotor, gpWrapper.left_axis_y, false);
        //rightDrive = new DcMotorWrapper(Hardware.instance.rightDriveMotor, gpWrapper.right_axis_y, true);

        leftPusher = new ServoWrapper(Hardware.instance.leftBeaconServo, gpWrapper.left_bumper);
        rightPusher = new ServoWrapper(Hardware.instance.rightBeaconServo, gpWrapper.right_bumper);
    }

    @Override
    public void loop() {
        Scheduler.instance.loop();
        //telemetry.addData(Hardware.tag, "light: " + Hardware.instance.lightSensor.getLightDetected());
    }
}
