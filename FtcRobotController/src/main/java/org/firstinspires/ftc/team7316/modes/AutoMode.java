package org.firstinspires.ftc.team7316.modes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.DriveDistanceAccurate;
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
        Hardware.setTelemetry(telemetry);

        leftMotor = Hardware.instance.leftDriveMotor;
        rightMotor = Hardware.instance.rightDriveMotor;

        Scheduler.instance.addTask(new DriveDistanceAccurate(11287, -0.6, Hardware.instance.leftDriveMotor));
        Scheduler.instance.addTask(new DriveDistanceAccurate(11287, -0.6, Hardware.instance.rightDriveMotor));
    }

    @Override
    public void loop() {
        Scheduler.instance.loop();
        Hardware.log(Hardware.tag, "Left Motor: " + Hardware.instance.leftDriveMotor.getCurrentPosition());
        Hardware.log(Hardware.tag, "Right Motor: " + Hardware.instance.rightDriveMotor.getCurrentPosition());
    }
}

