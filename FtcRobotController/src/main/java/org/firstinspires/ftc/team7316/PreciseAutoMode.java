package org.firstinspires.ftc.team7316;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.team7316.util.PreciseDriveDistance;
import org.firstinspires.ftc.team7316.util.Scheduler;

/**
 * Created by andrew on 9/27/16.
 */

@Autonomous(name = "PrecisePantherAuto")
public class PreciseAutoMode extends OpMode {

    private DcMotor leftMotor;
    private DcMotor rightMotor;

    @Override
    public void init() {
        Hardware.setHardwareMap(hardwareMap);

        leftMotor = Hardware.instance.leftDriveMotor;
        rightMotor = Hardware.instance.rightDriveMotor;

        Scheduler.instance.addTask(new PreciseDriveDistance(10000, 0.5, leftMotor));
        Scheduler.instance.addTask(new PreciseDriveDistance(10000, -0.5, rightMotor));
    }

    @Override
    public void loop() {
        Scheduler.instance.loop();
        telemetry.addData("Encoder Left", leftMotor.getCurrentPosition());
        telemetry.addData("Encoder Right", rightMotor.getCurrentPosition());
        telemetry.addData("Power Left", leftMotor.getPower());
        telemetry.addData("Power Right", rightMotor.getPower());
    }
}

