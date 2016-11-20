package org.firstinspires.ftc.team7316.util.commands;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;

import org.firstinspires.ftc.team7316.util.Loopable;

/**
 * Created by andrew on 11/1/16.
 */
public class TurnGyro implements Loopable {

    private DcMotor leftMotor;
    private DcMotor rightMotor;

    private GyroSensor gyro;

    private float remainingBearing;

    protected double power;
    protected float deltaBearing;

    /**
     *
     * @param deltaBearing never input a negative value for this
     * @param power don't turn in the inefficient direction, it will cause problems (deltaBearing < 180)
     * @param leftMotor
     * @param rightMotor
     * @param gyro
     */

    public TurnGyro(float deltaBearing, double power, DcMotor leftMotor, DcMotor rightMotor, GyroSensor gyro) { //+power = clockwise
        this.deltaBearing = deltaBearing;
        this.power = power;
        this.leftMotor = leftMotor;
        this.rightMotor = rightMotor;
        this.gyro = gyro;
    }

    @Override
    public void init() {
        this.gyro.resetZAxisIntegrator();
    }


    @Override
    public void loop() {
        this.leftMotor.setPower(this.power);
        this.rightMotor.setPower(-this.power);
        if (this.power > 0) {
            this.remainingBearing = this.deltaBearing - Math.abs(this.gyro.getHeading());
        } else {
            if (this.gyro.getHeading() < 180) {
                this.remainingBearing = this.deltaBearing - Math.abs(this.gyro.getHeading());
            } else {
                this.remainingBearing = this.deltaBearing - Math.abs(360 - this.gyro.getHeading());
            }
        }
    }


    @Override
    public boolean shouldRemove() {
        return this.remainingBearing < 0;
    }

    @Override
    public void terminate() {
        this.leftMotor.setPower(0);
        this.rightMotor.setPower(0);
    }

}
