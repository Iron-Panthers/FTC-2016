package org.firstinspires.ftc.team7316.util.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.team7316.util.Loopable;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.input.ToggleButtonWrapper;

/**
 * Created by andrew on 11/8/16.
 */
public class DcMotorToggleWrapper implements Loopable {

    private DcMotor motor;
    private double highPower;
    private double lowPower;
    private ToggleButtonWrapper button;

    public DcMotorToggleWrapper(DcMotor motor, double lowPower, double highPower, ToggleButtonWrapper button) {
        this.motor = motor;
        this.lowPower = lowPower;
        this.highPower = highPower;
        this.button = button;
    }

    @Override
    public void init() {
        this.motor.setPower(lowPower);
    }

    @Override
    public void loop() {
        if (button.isPressed()) {
            this.motor.setPower(highPower);
        } else {
            this.motor.setPower(lowPower);
        }
    }

    @Override
    public boolean shouldRemove() {
        return false;
    }

    @Override
    public void terminate() {
        this.motor.setPower(0);
    }
}
