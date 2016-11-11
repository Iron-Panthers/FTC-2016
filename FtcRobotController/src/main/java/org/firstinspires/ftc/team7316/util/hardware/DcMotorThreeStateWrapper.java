package org.firstinspires.ftc.team7316.util.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.team7316.util.Loopable;
import org.firstinspires.ftc.team7316.util.input.TwoButtonToggleWrapper;

/**
 * Created by andrew on 11/10/16.
 */
public class DcMotorThreeStateWrapper implements Loopable {

    private TwoButtonToggleWrapper buttons;
    private DcMotor motor;
    private double forwardPower, neutralPower, reversePower;

    public DcMotorThreeStateWrapper(DcMotor motor, double forwardPower, double neutralPower, double reversePower, TwoButtonToggleWrapper buttons) {
        this.motor = motor;
        this.buttons = buttons;
        this.forwardPower = forwardPower;
        this.neutralPower = neutralPower;
        this.reversePower = reversePower;
    }

    @Override
    public void init() {
        this.motor.setPower(0);
    }

    @Override
    public void loop() {
        switch (buttons.buttonsState()) {
            case NEUTRAL:
                motor.setPower(neutralPower);
            case FORWARD:
                motor.setPower(forwardPower);
            case BACKWARD:
                motor.setPower(reversePower);
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
