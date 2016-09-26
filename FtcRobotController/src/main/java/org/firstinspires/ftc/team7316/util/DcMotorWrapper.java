package org.firstinspires.ftc.team7316.util;

import com.qualcomm.robotcore.hardware.DcMotor;

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

    public boolean shouldRemove() {
        return false;
    }

    @Override
    public void terminate() {

    }

}
