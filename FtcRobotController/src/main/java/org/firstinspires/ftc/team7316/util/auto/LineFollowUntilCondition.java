package org.firstinspires.ftc.team7316.util.auto;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.LightSensor;

import org.firstinspires.ftc.team7316.util.Loopable;

/**
 * Created by andrew on 11/2/16.
 */
public class LineFollowUntilCondition extends LineFollow {

    private Loopable condition;

    public LineFollowUntilCondition(DcMotor leftMotor, DcMotor rightMotor, LightSensor sensor, double wantedPower, Loopable condition) {
        super(leftMotor, rightMotor, sensor, wantedPower);
        this.condition = condition;
    }

    @Override
    public boolean shouldRemove() {
        return condition.shouldRemove();
    }
}
