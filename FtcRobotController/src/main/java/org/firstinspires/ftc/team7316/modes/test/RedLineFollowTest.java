package org.firstinspires.ftc.team7316.modes.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.team7316.modes.CommandAuto;
import org.firstinspires.ftc.team7316.util.Alliance;
import org.firstinspires.ftc.team7316.util.Loopable;
import org.firstinspires.ftc.team7316.util.commands.drive.LineFollow;
import org.firstinspires.ftc.team7316.util.hardware.Hardware;

/**
 * Created by andrew on 1/27/17.
 */
@Autonomous(name="red line follow test <---- use this")
public class RedLineFollowTest extends CommandAuto {

    @Override
    protected Loopable getTask() {
        return new LineFollow(Hardware.instance.leftDriveMotor, Hardware.instance.rightDriveMotor, Hardware.instance.lightSensorLeft, 0.15, Alliance.RED);
    }

}
