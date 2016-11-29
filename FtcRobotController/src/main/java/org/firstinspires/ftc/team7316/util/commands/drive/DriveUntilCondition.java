package org.firstinspires.ftc.team7316.util.commands.drive;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.team7316.util.Loopable;
import org.firstinspires.ftc.team7316.util.commands.conditions.Conditional;

/**
 * Created by andrew on 11/11/16.
 */
public class DriveUntilCondition extends DriveDistance implements Loopable {

    private double power;
    private DcMotor motor;
    private Conditional condition;

    public DriveUntilCondition(double power, DcMotor motor, Conditional condition) {
        super(0, power, motor);
        this.condition = condition;
        this.power = power;
        this.motor = motor;
    }

    @Override
    public boolean shouldRemove() {
        return condition.shouldRemove();
    }


}
