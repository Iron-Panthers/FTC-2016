package org.firstinspires.ftc.team7316.modes.auto;

import org.firstinspires.ftc.team7316.modes.CommandAuto;
import org.firstinspires.ftc.team7316.util.Loopable;
import org.firstinspires.ftc.team7316.util.commands.AutoCodes;

/**
 * Created by Maxim on 1/24/2017.
 */

public class WaitAndParkCenter extends CommandAuto {

    protected WaitAndParkCenter() {
        super(AutoCodes.waitAndDrive(0, 0, 1));
    }

}
