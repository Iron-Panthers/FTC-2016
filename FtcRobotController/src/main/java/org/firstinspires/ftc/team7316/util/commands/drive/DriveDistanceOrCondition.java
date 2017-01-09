package org.firstinspires.ftc.team7316.util.commands.drive;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.team7316.util.commands.conditions.Conditional;

/**
 * Created by andrew on 12/18/16.
 */
public class DriveDistanceOrCondition extends DriveDistanceAccurate {
    private Conditional conditional;

    public DriveDistanceOrCondition(double distance, double power, DcMotor motor, Conditional conditional) {
        super(distance, power, motor);
        this.conditional = conditional;
    }

    @Override
    public boolean shouldRemove() {
        return super.shouldRemove() || this.conditional.state();
    }
}
