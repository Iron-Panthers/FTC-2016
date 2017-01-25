package org.firstinspires.ftc.team7316.util.commands.turn;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;

import org.firstinspires.ftc.team7316.util.commands.conditions.Conditional;

/**
 * Created by andrew on 1/21/17.
 */
public class RightHoldTurnUntilConditional extends TurnGyro {

    private Conditional conditional;

    /**
     * @param deltaBearing never input a negative value for this
     * @param power        don't turn in the inefficient direction, it will cause problems (deltaBearing < 180)
     * @param leftMotor
     * @param rightMotor
     * @param gyro
     */
    public RightHoldTurnUntilConditional(float deltaBearing, double power, DcMotor leftMotor, DcMotor rightMotor, GyroSensor gyro, Conditional conditional) {
        super(deltaBearing, power, leftMotor, rightMotor, gyro);
        this.conditional = conditional;
    }

    @Override
    public boolean shouldRemove() {
        return super.shouldRemove() || conditional.state();
    }

    @Override
    public void loop() {
        super.leftMotor.setPower(this.power);
        super.rightMotor.setPower(0);
    }
}
