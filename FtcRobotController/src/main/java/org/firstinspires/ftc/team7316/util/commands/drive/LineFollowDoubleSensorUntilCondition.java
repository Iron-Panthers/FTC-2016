package org.firstinspires.ftc.team7316.util.commands.drive;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.LightSensor;

import org.firstinspires.ftc.team7316.util.Alliance;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.conditions.Conditional;
import org.firstinspires.ftc.team7316.util.input.ButtonListener;

/**
 * Created by Alec on 1/17/2017.
 */

public class LineFollowDoubleSensorUntilCondition extends LineFollowDoubleSensor implements ButtonListener {

    private Conditional condition;
    private boolean needRemove = false;

    public LineFollowDoubleSensorUntilCondition (DcMotor leftMotor, DcMotor rightMotor, LightSensor leftSensor,  LightSensor rightSensor, double wantedPower, Conditional condition) {
        super(leftMotor, rightMotor, leftSensor, rightSensor, wantedPower);
        this.condition = condition;
    }

    @Override
    public boolean shouldRemove() {
        if (needRemove) {
            needRemove = false;
            return true;
        }
        return condition.state();
    }

    @Override
    public void onPressed() {
        needRemove = false;
        Scheduler.instance.addTask(this);
    }

    @Override
    public void onReleased() {
        needRemove = true;
    }
}
