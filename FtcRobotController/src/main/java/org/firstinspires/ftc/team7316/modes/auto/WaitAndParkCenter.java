package org.firstinspires.ftc.team7316.modes.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.team7316.modes.CommandAuto;
import org.firstinspires.ftc.team7316.util.Loopable;
import org.firstinspires.ftc.team7316.util.commands.AutoCodes;

/**
 * Created by Maxim on 1/24/2017.
 */
@Autonomous(name="Wait and Park Center")
public class WaitAndParkCenter extends CommandAuto {

    @Override
    protected Loopable getTask() {
        return AutoCodes.waitAndDriveToCenter(1);
    }
}
