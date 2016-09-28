package org.firstinspires.ftc.team7316;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.team7316.util.Loopable;

/**
 * Created by Maxim on 9/27/2016.
 */
public class DriveDistance implements Loopable {

    private DcMotor motor;

    private boolean reachedDistance = false;
    private float power;
    private float wantedDist;

    public DriveDistance (float dist, float power, MOTOR motor) {
        wantedDist = dist;
        this.power = power;
        this.motor = motor;
    }

    @Override
    public void loop() {
        this.motor.SETPOWER(this.power)
        if (this.motor.CURRENTENCODERDIST() > this.wantedDist) {
            reachedDistance = true;
        }
    }

    @Override
    public boolean shouldRemove() {
        return reachedDistance;
    }

    @Override
    public void terminate() {
    }

    @Override
    public void init() {
        this.wantedDist += this.moto.CURRENTENCODERDIST();
    }

}