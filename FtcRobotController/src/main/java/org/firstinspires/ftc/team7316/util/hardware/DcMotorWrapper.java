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

    public DcMotorWrapper(DcMotor motor, AxisWrapper axis) {
        this.motor = motor;
        this.axis = axis;

        Scheduler.instance.addTask(this);
    }

    @Override
    public void init() {

    }

    public void loop() {
        motor.setPower(axis.value());
    }

    public int getEncoderPos() {
        return motor.getCurrentPosition();
    }

    public boolean shouldRemove() {
        return false;
    }

    @Override
    public void terminate() {

    }

}
