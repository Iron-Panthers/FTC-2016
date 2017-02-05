package org.firstinspires.ftc.team7316.util.commands.drive;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.team7316.util.commands.conditions.Conditional;

/**
 * Created by andrew on 2/4/17.
 */
public class DriveDistanceLameOrConditional extends DriveDistance {

    private Conditional conditional;

    public DriveDistanceLameOrConditional(float distance, double power, DcMotor motor, Conditional conditional) {
        super(distance, power, motor);
        this.conditional = conditional;
    }

    @Override
    public boolean shouldRemove() {
        return super.shouldRemove() || this.conditional.state();
    }

}
