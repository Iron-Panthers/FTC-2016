package org.firstinspires.ftc.team7316.util.commands.drive;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.team7316.util.Loopable;

/**
 * Created by Maxim on 9/27/2016.
 */
public class DriveDistance implements Loopable {

    private DcMotor motor;

    private double power;
    private double wantedDist;

    public DriveDistance (double dist, double power, DcMotor motor) {
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
        this.motor.setPower(this.power);
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