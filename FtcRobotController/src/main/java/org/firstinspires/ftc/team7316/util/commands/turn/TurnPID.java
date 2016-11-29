package org.firstinspires.ftc.team7316.util.commands.turn;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.team7316.util.Loopable;

public class TurnPID implements Loopable {

    public static final float P = 1, I = 0, D = 0;

    private DcMotor left, right;
    private GyroSensor gyro;
    private ElapsedTime timer;
    private int target;
    private float sumError, lastError;

    @Override
    public void init() {
        sumError = 0;
        lastError = error(gyro.getHeading());
    }

    @Override
    public void loop() {
        double deltaTime = timer.time();
        double error = error(gyro.getHeading());
        double deltaError = (error - lastError) / deltaTime;
        
        timer.reset();
    }

    @Override
    public boolean shouldRemove() {
        return false;
    }

    @Override
    public void terminate() {

    }

    private float error(int input) {
        return (input + 180 - target) % 360;
    }

}
