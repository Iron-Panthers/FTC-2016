package org.firstinspires.ftc.team7316.util;

import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Maxim on 9/27/2016.
 */
public class PreciseDriveDistance implements Loopable {

    private static final float finalApproach = 2048, minPower = 0.15f;

    private DcMotor motor;

    private double power;
    private float wantedDist;
    private float lastLooped;

    public PreciseDriveDistance(float dist, double power, DcMotor motor) {
        wantedDist = dist;
        this.power = power;
        this.motor = motor;
    }

    @Override
    public void init() {
        this.wantedDist += Math.abs(this.motor.getCurrentPosition());
    }

    @Override
    public void loop() {
        if (Math.abs(this.motor.getCurrentPosition()) > this.wantedDist - PreciseDriveDistance.finalApproach) {
            power = power > 0 ? minPower : -minPower;
        }
        this.motor.setPower(power);
    }

    @Override
    public boolean shouldRemove() {
        return Math.abs(this.motor.getCurrentPosition()) > this.wantedDist;
    }

    @Override
    public void terminate() {
        this.motor.setPower(0);
    }

}