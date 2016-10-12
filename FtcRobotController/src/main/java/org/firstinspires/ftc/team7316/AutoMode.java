package org.firstinspires.ftc.team7316;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.team7316.util.PreciseDriveDistance;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.auto.DriveDistanceAccurate;
import org.firstinspires.ftc.team7316.util.auto.LineFollow;
import org.firstinspires.ftc.team7316.util.hardware.Hardware;

/**
 * Created by andrew on 9/27/16.
 */

@Autonomous(name = "PantherAuto")
public class AutoMode extends OpMode {

    private DcMotor leftMotor;
    private DcMotor rightMotor;

    @Override
    public void init() {
        Scheduler.instance.clear();

        Hardware.setHardwareMap(hardwareMap);

        leftMotor = Hardware.instance.leftDriveMotor;
        rightMotor = Hardware.instance.rightDriveMotor;

        Scheduler.instance.addTask(new LineFollow(leftMotor, false, rightMotor, false, Hardware.instance.lightSensor, 0.3));
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

