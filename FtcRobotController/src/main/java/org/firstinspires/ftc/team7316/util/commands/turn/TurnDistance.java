package org.firstinspires.ftc.team7316.util.commands.turn;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.team7316.util.Loopable;

/**
 * It's theoretical, and it's auto, and it's by andrew.
 */
public class TurnDistance implements Loopable {

    private DcMotor lM;
    private DcMotor rM;

    private boolean reachedAngle = false;
    private float power;
    private float wantedAngle;
    private float startAngle;

    public TurnDistance(float degrees, float power, DcMotor leftMotor, DcMotor rightMotor) {
        wantedAngle = degrees;
        this.power = power;

        if (degrees < 0) {
            this.power *= -1;
        }

        this.lM = leftMotor;
        this.rM = rightMotor;
    }

    @Override
    public void loop() {
        this.lM.setPower(this.power);
        this.rM.setPower(this.power);
        if (Math.abs(this.startAngle - 0) > this.wantedAngle) {
            reachedAngle = true;
        }
    }

    @Override
    public boolean shouldRemove() {
        return reachedAngle;
    }

    @Override
    public void terminate() {
    }

    @Override
    public void init() {
        this.startAngle = 0;
    }

}

