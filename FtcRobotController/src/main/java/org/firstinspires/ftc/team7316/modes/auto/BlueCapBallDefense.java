package org.firstinspires.ftc.team7316.modes.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.team7316.modes.CommandAuto;
import org.firstinspires.ftc.team7316.util.Loopable;
import org.firstinspires.ftc.team7316.util.commands.AutoCodes;

/**
 * Created by andrew on 2/16/17.
 */
@Autonomous(name = "B s troll")
public class BlueCapBallDefense extends CommandAuto {
    @Override
    protected Loopable getTask() {
        return AutoCodes.bluePushYogaBall();
    }
}
