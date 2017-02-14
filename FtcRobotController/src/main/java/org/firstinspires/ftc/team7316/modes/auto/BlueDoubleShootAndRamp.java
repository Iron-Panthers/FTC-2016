package org.firstinspires.ftc.team7316.modes.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.team7316.modes.CommandAuto;
import org.firstinspires.ftc.team7316.util.Loopable;
import org.firstinspires.ftc.team7316.util.commands.AutoCodes;

/**
 * Created by Maxim on 2/13/2017.
 */
@Autonomous(name="Blue Double Shoot and Ramp")
public class BlueDoubleShootAndRamp extends CommandAuto {
    @Override
    protected Loopable getTask() {
        return AutoCodes.blueDoubleShootAndRamp();
    }
}
