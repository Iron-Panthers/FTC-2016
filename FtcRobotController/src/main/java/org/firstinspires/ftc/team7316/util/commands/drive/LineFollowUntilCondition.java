package org.firstinspires.ftc.team7316.util.commands.drive;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.LightSensor;

import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.conditions.Conditional;
import org.firstinspires.ftc.team7316.util.input.ButtonListener;

/**
 * Created by andrew on 11/2/16.
 */
public class LineFollowUntilCondition extends LineFollow implements ButtonListener {

    private Conditional condition;
    private boolean needRemove = false;

    public LineFollowUntilCondition(DcMotor leftMotor, DcMotor rightMotor, LightSensor sensor, double wantedPower, Conditional condition) {
        super(leftMotor, rightMotor, sensor, wantedPower);
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
        Scheduler.instance.addTask(this);
    }

    @Override
    public void onReleased() {
        needRemove = true;
    }
}
