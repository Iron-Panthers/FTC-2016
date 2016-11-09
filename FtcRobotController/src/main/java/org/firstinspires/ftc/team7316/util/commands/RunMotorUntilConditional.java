package org.firstinspires.ftc.team7316.util.commands;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.team7316.util.Loopable;
import org.firstinspires.ftc.team7316.util.commands.conditions.Conditional;

/**
 * Created by andrew on 11/8/16.
 */
public class RunMotorUntilConditional implements Loopable {

    private DcMotor motor;
    private Conditional terminalCondition;
    private double power;

    public RunMotorUntilConditional (DcMotor motor, Conditional terminalCondition, double power) {
        this.motor = motor;
        this.terminalCondition = terminalCondition;
        this.power = power;
    }

    @Override
    public void init() {

    }

    @Override
    public void loop() {
        this.motor.setPower(this.power);
    }

    @Override
    public boolean shouldRemove() {
        return terminalCondition.shouldRemove();
    }

    @Override
    public void terminate() {
        this.motor.setPower(0);
    }
}
