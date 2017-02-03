package org.firstinspires.ftc.team7316.util.commands.drive;

import android.graphics.Color;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.LightSensor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;

import org.firstinspires.ftc.team7316.util.Alliance;
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

    public LineFollowUntilCondition(DcMotor leftMotor, DcMotor rightMotor, LightSensor sensor, double wantedPower, Conditional condition, Alliance color) {
        super(leftMotor, rightMotor, sensor, wantedPower, color);
        this.condition = condition;
    }

    public LineFollowUntilCondition(DcMotor leftDriveMotor, DcMotor rightDriveMotor, LightSensor lightSensor, double wantedPower, Conditional buttonTriggered, double p, double i, double d, double maxPower, double minPower) {
        super(leftDriveMotor, rightDriveMotor, lightSensor, wantedPower, Alliance.BLUE, p, i, d, minPower, maxPower);
        this.condition = buttonTriggered;
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
