package org.firstinspires.ftc.team7316.modes.test;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.AutoCodes;
import org.firstinspires.ftc.team7316.util.commands.drive.DriveDistancePID;
import org.firstinspires.ftc.team7316.util.commands.flow.SimultaneousCommands;
import org.firstinspires.ftc.team7316.util.hardware.Hardware;

@Disabled
@TeleOp(name="DriveDistanceTest")
public class DriveDistanceTestMode extends OpMode {

    double dist = 5.5;

    @Override
    public void init() {
        Hardware.setHardwareMap(this.hardwareMap);
        SimultaneousCommands drive = AutoCodes.robotDriveDistanceAccurate(dist,0.4);
        Scheduler.instance.addTask(drive);
    }

    @Override
    public void loop() {
        Scheduler.instance.loop();
    }

}
