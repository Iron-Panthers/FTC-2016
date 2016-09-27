package org.firstinspires.ftc.team7316.util.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.team7316.util.Loopable;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.input.AxisWrapper;

/**
 * Created by andrew on 9/15/16.
 */
public class DcMotorWrapper implements Loopable {

    private DcMotor motor;
    private AxisWrapper axis;
    private boolean isReversed;

    public DcMotorWrapper(DcMotor motor, AxisWrapper axis, boolean isReversed) {
        this.motor = motor;
        this.axis = axis;
        this.isReversed = isReversed;

        Scheduler.instance.addTask(this);
    }

    @Override
    public void init() {

    }

    public void loop() {

        if (isReversed) {
            motor.setPower(-axis.value());
        } else {
            motor.setPower(axis.value());
        }

    }

    public void setTargetPosition(int position) {
        if (isReversed) {
            motor.setTargetPosition(-position);
        } else {
            motor.setTargetPosition(position);
        }
    }

    public int getEncoderPos() {
        if (isReversed) {
            return -motor.getCurrentPosition();
        }
        return motor.getCurrentPosition();
    }

    public boolean shouldRemove() {
        return false;
    }

    @Override
    public void terminate() {

    }

}
