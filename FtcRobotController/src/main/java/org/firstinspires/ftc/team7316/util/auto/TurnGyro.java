package org.firstinspires.ftc.team7316.util.auto;

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
    protected float startBearing;

    private float deltaOffset = 0;

    //This doesn't really work


    public TurnGyro(float deltaBearing, double power, DcMotor leftMotor, DcMotor rightMotor, GyroSensor gyro) { //+power = clockwise
        this.deltaBearing = deltaBearing;
        this.power = power;
        this.leftMotor = leftMotor;
        this.rightMotor = rightMotor;
        this.gyro = gyro;
    }

    @Override
    public void init() {
        this.startBearing = this.gyro.getHeading();
    }


    @Override
    public void loop() {
        this.leftMotor.setPower(this.power);
        this.rightMotor.setPower(-this.power);
        this.remainingBearing = this.deltaBearing - Math.abs(this.gyro.getHeading() - this.startBearing);
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
