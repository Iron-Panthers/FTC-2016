package org.firstinspires.ftc.team7316.modes.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.team7316.modes.CommandAuto;
import org.firstinspires.ftc.team7316.util.Loopable;
import org.firstinspires.ftc.team7316.util.commands.AutoCodes;
import org.firstinspires.ftc.team7316.util.hardware.Hardware;

/**
 * Created by andrew on 2/16/17.
 */
@Autonomous(name = "Red Cap Ball Defense")
public class RedCapBallDefense extends CommandAuto {
    @Override
    protected Loopable getTask() {
        return AutoCodes.redPushYogaBall();
    }

    public void onLoop() {
        Hardware.log("left", Hardware.instance.whackerLeft.isPressed());
        Hardware.log("right", Hardware.instance.whackerRight.isPressed());
    }
}
