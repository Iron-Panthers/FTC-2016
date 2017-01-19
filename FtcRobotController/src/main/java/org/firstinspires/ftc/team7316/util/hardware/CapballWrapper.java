package org.firstinspires.ftc.team7316.util.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Loopable;
import org.firstinspires.ftc.team7316.util.input.ButtonWrapper;

/**
 * Created by Maxim on 1/17/2017.
 */

public class CapballWrapper implements Loopable {

    private DcMotor motor;
    private ButtonWrapper upButton, downButton;

    public CapballWrapper(DcMotor motor, ButtonWrapper upButton, ButtonWrapper downButton) {
        this.motor = motor;
        this.upButton = upButton;
        this.downButton = downButton;
    }

    @Override
    public void init() {

    }

    @Override
    public void loop() {
        Hardware.log(Hardware.tag, "Encoder: " + this.motor.getCurrentPosition());

        if (this.motor.getCurrentPosition() > 0 && downButton.state()) {
            this.motor.setPower(-Constants.CAP_BALL_SPEED);
        } else if (this.motor.getCurrentPosition() < Constants.CAP_BALL_TOP_LIMIT && upButton.state()) {
            this.motor.setPower(Constants.CAP_BALL_SPEED);
        } else {
            this.motor.setPower(0);
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
