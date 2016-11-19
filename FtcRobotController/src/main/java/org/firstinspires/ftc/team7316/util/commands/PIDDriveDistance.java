package org.firstinspires.ftc.team7316.util.commands;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.team7316.util.Loopable;

/**
 * Created by Maxim on 9/27/2016.
 */
public class PIDDriveDistance implements Loopable {

    private static final double speedThresh = 10;
    private static final int posThresh = 25;

    private DcMotor motor;

    private int target, lastPosition;
    private long lastTime;
    private double speed, power;

    public PIDDriveDistance(int dist, double power, DcMotor motor) {
        this.target = dist;
        this.motor = motor;
        this.power = power;
    }// Junha made this comment hehe xd

    @Override
    public void init() {
        motor.setTargetPosition(target);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor.setPower(power);
        lastPosition = motor.getCurrentPosition();
        lastTime = System.currentTimeMillis();
    }

    @Override
    public void loop() {
        int currentPosition = motor.getCurrentPosition();
        long currentTime = System.currentTimeMillis();
        speed = 1000 * (((double) currentPosition - lastPosition) / (currentTime - lastTime));
        lastTime = currentTime;
        lastPosition = currentPosition;
    }

    @Override
    public boolean shouldRemove() {
        return Math.abs(speed) < speedThresh && Math.abs(motor.getCurrentPosition() - target) <  posThresh;
    }

    @Override
    public void terminate() {
        this.motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

}