package org.firstinspires.ftc.team7316;

import android.os.Debug;
import android.util.Log;

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

    public void loop() {
        motor.setPower(axis.value());
    }

    public boolean shouldRemove() {
        return false;
    }

}
