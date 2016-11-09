package org.firstinspires.ftc.team7316.util.auto;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.LightSensor;

import org.firstinspires.ftc.team7316.util.Loopable;
import org.firstinspires.ftc.team7316.util.auto.conditions.Conditional;

import java.util.concurrent.locks.Condition;

/**
 * Created by andrew on 11/2/16.
 */
public class LineFollowUntilCondition extends LineFollow {

    private Conditional condition;

    public LineFollowUntilCondition(DcMotor leftMotor, DcMotor rightMotor, LightSensor sensor, double wantedPower, Conditional condition) {
        super(leftMotor, rightMotor, sensor, wantedPower);
        this.condition = condition;
    }

    @Override
    public boolean shouldRemove() {
        return condition.shouldRemove();
    }
}
