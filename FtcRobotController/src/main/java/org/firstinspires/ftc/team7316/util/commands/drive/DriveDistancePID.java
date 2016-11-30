package org.firstinspires.ftc.team7316.util.commands.drive;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.team7316.util.Loopable;

/**
 * Let's hope that unlike last time, this <i>actually works.</i>
 */
public class DriveDistancePID implements Loopable {

    public static final double P = 0, I = 0, D = 0;
    public static final int ERROR_THRESHOLD = 100, DELTA_THRESHOLD = 10;

    private DcMotor motor;
    private int target;
    public double deltaError, lastError, sumError;
    private ElapsedTime timer;

    public DriveDistancePID(DcMotor motor, int target) {
        this.motor = motor;
        this.target = target;
        this.timer = new ElapsedTime();
    }

    public void init() {
        sumError = 0;
        lastError = error();
        deltaError = 0;
        timer.reset();
    }

    @Override
    public void loop() {
        double deltaTime = timer.time();
        double error = error();
        deltaError = (error - lastError) / deltaTime;
        sumError += deltaError;

        double power = P*error + I*sumError + D*deltaError;
        motor.setPower(power);

        lastError = error;
        timer.reset();
    }

    @Override
    public boolean shouldRemove() {
        //return false;
        return Math.abs(error() - target) <= ERROR_THRESHOLD && Math.abs(deltaError) <= DELTA_THRESHOLD;
    }

    @Override
    public void terminate() {
        motor.setPower(0);
    }

    private int error() {
        return motor.getCurrentPosition() - target;
    }

}
