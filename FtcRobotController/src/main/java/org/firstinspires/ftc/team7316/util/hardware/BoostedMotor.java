package org.firstinspires.ftc.team7316.util.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareDevice;

/**
 * Created by andrew on 1/11/17.
 */
public class BoostedMotor implements DcMotor {

    private DcMotor motor;
    private double boost = 1;

    public BoostedMotor(DcMotor motor, double boost) {
        this.motor = motor;
        this.boost = boost;
    }

    @Override
    public void setMaxSpeed(int encoderTicksPerSecond) {
        this.motor.setMaxSpeed(encoderTicksPerSecond);
    }

    @Override
    public int getMaxSpeed() {
        return this.motor.getMaxSpeed();
    }

    @Override
    public DcMotorController getController() {
        return this.motor.getController();
    }

    @Override
    public int getPortNumber() {
        return this.motor.getPortNumber();
    }

    @Override
    public void setZeroPowerBehavior(ZeroPowerBehavior zeroPowerBehavior) {
        this.motor.setZeroPowerBehavior(zeroPowerBehavior);
    }

    @Override
    public ZeroPowerBehavior getZeroPowerBehavior() {
        return this.motor.getZeroPowerBehavior();
    }

    @Override
    public void setPowerFloat() {
        this.motor.setPowerFloat();
    }

    @Override
    public boolean getPowerFloat() {
        return this.motor.getPowerFloat();
    }

    @Override
    public void setTargetPosition(int position) {
        this.motor.setTargetPosition(position);
    }

    @Override
    public int getTargetPosition() {
        return this.motor.getTargetPosition();
    }

    @Override
    public boolean isBusy() {
        return this.motor.isBusy();
    }

    @Override
    public int getCurrentPosition() {
        return this.motor.getCurrentPosition();
    }

    @Override
    public void setMode(RunMode mode) {
        this.motor.setMode(mode);
    }

    @Override
    public RunMode getMode() {
        return this.motor.getMode();
    }

    @Override
    public void setDirection(Direction direction) {
        this.motor.setDirection(direction);
    }

    @Override
    public Direction getDirection() {
        return this.motor.getDirection();
    }

    @Override
    public void setPower(double power) {
        this.motor.setPower(power*this.boost);
    }

    @Override
    public double getPower() {
        return this.motor.getPower();
    }

    @Override
    public Manufacturer getManufacturer() {
        return this.motor.getManufacturer();
    }

    @Override
    public String getDeviceName() {
        return this.motor.getDeviceName();
    }

    @Override
    public String getConnectionInfo() {
        return this.motor.getConnectionInfo();
    }

    @Override
    public int getVersion() {
        return this.motor.getVersion();
    }

    @Override
    public void resetDeviceConfigurationForOpMode() {
        this.resetDeviceConfigurationForOpMode();
    }

    @Override
    public void close() {
        this.motor.close();
    }
}
