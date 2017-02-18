package org.firstinspires.ftc.team7316.util.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.team7316.util.LateralDirection;
import org.firstinspires.ftc.team7316.util.Loopable;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.LogTelemetryCommand;
import org.firstinspires.ftc.team7316.util.commands.conditions.ButtonCondition;
import org.firstinspires.ftc.team7316.util.commands.drive.RunMotorUntilConditional;
import org.firstinspires.ftc.team7316.util.input.ButtonListener;

/**
 * Created by Maxim on 2/17/2017.
 */

public class WhackerWrapper implements ButtonListener, Loopable {

    private DcMotor dcMotor;
    private Loopable currentCommand;
    private TouchSensor left, right;

    public WhackerWrapper(DcMotor dcMotor, TouchSensor left, TouchSensor right) {
        this.dcMotor = dcMotor;
        this.left = left;
        this.right = right;
    }

    public Loopable moveToCommand(LateralDirection side, double power) {
        switch (side) {
            case LEFT:
                return new RunMotorUntilConditional(Hardware.instance.capBallWhackerMotor, new ButtonCondition(left), -power);
            case RIGHT:
                return new RunMotorUntilConditional(Hardware.instance.capBallWhackerMotor, new ButtonCondition(right), power);
            default:
                Hardware.memel0rd();
        }
        return Loopable.BLANK;
    }

    @Override
    public void onPressed() {
        if (currentCommand != null) {
            currentCommand.terminate();
        }
        currentCommand = moveToCommand(LateralDirection.LEFT, 1);
        currentCommand.init();
    }

    @Override
    public void onReleased() {
        if (currentCommand != null) {
            currentCommand.terminate();
        }
        currentCommand = moveToCommand(LateralDirection.RIGHT, 1);
        currentCommand.init();
    }

    @Override
    public void init() {
        currentCommand = moveToCommand(LateralDirection.RIGHT, 1);
    }

    @Override
    public void loop() {

        if (currentCommand != null){
            currentCommand.loop();
            if (currentCommand.shouldRemove()) {
                currentCommand.terminate();
                currentCommand = null;
            }
        }
    }

    @Override
    public boolean shouldRemove() {
        return false;
    }

    @Override
    public void terminate() {
        currentCommand.terminate();
    }
}
