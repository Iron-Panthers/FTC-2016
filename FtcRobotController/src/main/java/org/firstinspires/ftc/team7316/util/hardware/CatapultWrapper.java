package org.firstinspires.ftc.team7316.util.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.team7316.util.Loopable;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.drive.RunMotorForTime;
import org.firstinspires.ftc.team7316.util.commands.drive.RunMotorUntilConditional;
import org.firstinspires.ftc.team7316.util.commands.conditions.Conditional;
import org.firstinspires.ftc.team7316.util.input.AxisWrapper;
import org.firstinspires.ftc.team7316.util.input.ButtonListener;

/**
 * Created by Maxim on 11/8/2016.
 */
public class CatapultWrapper implements ButtonListener, Loopable {

    private DcMotor motor;
    private Conditional primedState;
    private Conditional blockCondition;
    private Loopable currentCommand;
    private AxisWrapper override;
    private double overrideThreshold = 0.1;
    public boolean isPrimed = false;

    public CatapultWrapper(DcMotor motor, Conditional primedState, AxisWrapper override, Conditional blockCondition) {
        this.motor = motor;
        this.primedState = primedState;
        this.override = override;
        this.blockCondition = blockCondition;
    }

    @Override
    public void onPressed() {

        if (currentCommand == null) {  // Is the motor in use?
            if (primedState.state() && !blockCondition.state()) {  // Is the shooter currently primed?
                currentCommand = new RunMotorForTime(motor, 1, 1);
                this.isPrimed = false;
                Scheduler.instance.addTask(currentCommand);
            } else {
                currentCommand = new RunMotorUntilConditional(motor, primedState, 1);
                this.isPrimed = true;
                Scheduler.instance.addTask(currentCommand);
            }
        }
    }

    @Override
    public void onReleased() {

    }

    @Override
    public void init() {

    }

    @Override
    public void loop() {
        if (Math.abs(override.value()) > this.overrideThreshold) {
            this.motor.setPower(override.value());
        } else {
            if (currentCommand == null) {
                this.motor.setPower(0);
            }
        }

        if (currentCommand != null && currentCommand.shouldRemove()) {
            currentCommand = null; // Clear the lock
        }
    }

    @Override
    public boolean shouldRemove() {
        return false;
    }

    @Override
    public void terminate() {

    }
}
