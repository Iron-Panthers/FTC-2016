package org.firstinspires.ftc.team7316.modes.test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.drive.DriveDistancePID;
import org.firstinspires.ftc.team7316.util.hardware.Hardware;

@TeleOp(name="DriveDistanceTest")
public class DriveDistanceTestMode extends OpMode {

    DriveDistancePID left, right;
    int dist = 5430;

    @Override
    public void init() {
        Hardware.setHardwareMap(this.hardwareMap);
        left = new DriveDistancePID(Hardware.instance.leftDriveMotor, dist);
        right = new DriveDistancePID(Hardware.instance.rightDriveMotor, dist);
        Scheduler.instance.addTask(left);
        Scheduler.instance.addTask(right);
    }

    @Override
    public void loop() {
        Scheduler.instance.loop();
    }

}
