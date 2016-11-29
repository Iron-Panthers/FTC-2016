package org.firstinspires.ftc.team7316.util.commands.drive;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.team7316.util.Loopable;

/**
 * Created by Maxim on 11/8/2016.
 */
public class RunMotorForTime implements Loopable {

    private DcMotor motor;
    private ElapsedTime time;
    private double power, duration;

    public RunMotorForTime(DcMotor motor, double power, double duration) {
        this.motor = motor;
        this.power = power;
        this.duration = duration;
        this.time = new ElapsedTime();
    }

    @Override
    public void init() {
        motor.setPower(power);
        time.reset();
    }

    @Override
    public void loop() {

    }

    @Override
    public boolean shouldRemove() {
        return time.seconds() >= duration;
    }

    @Override
    public void terminate() {
        motor.setPower(0);
    }

}
