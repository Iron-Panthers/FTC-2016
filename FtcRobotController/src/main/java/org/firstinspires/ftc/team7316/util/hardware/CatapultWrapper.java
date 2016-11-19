package org.firstinspires.ftc.team7316.util.hardware;

import android.util.Log;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.team7316.util.Loopable;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.RunMotorForTime;
import org.firstinspires.ftc.team7316.util.commands.RunMotorUntilConditional;
import org.firstinspires.ftc.team7316.util.commands.conditions.Conditional;
import org.firstinspires.ftc.team7316.util.input.ButtonListener;

/**
 * Created by Maxim on 11/8/2016.
 */
public class CatapultWrapper implements ButtonListener, Loopable {

    private DcMotor motor;
    private Conditional primedState;
    private Loopable currentCommand;

    public CatapultWrapper(DcMotor motor, Conditional primedState) {
        this.motor = motor;
        this.primedState = primedState;
    }

    @Override
    public void onPressed() {
        Hardware.log("currentcommand", currentCommand instanceof RunMotorForTime);
        Hardware.log("conditional", primedState.shouldRemove());
        if (currentCommand == null) {  // Is the motor in use?
            if (primedState.shouldRemove()) {  // Is the shooter currently primed?
                currentCommand = new RunMotorForTime(motor, 1, 1);
                Scheduler.instance.addTask(currentCommand);
            } else {
                currentCommand = new RunMotorUntilConditional(motor, primedState, 1);
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
