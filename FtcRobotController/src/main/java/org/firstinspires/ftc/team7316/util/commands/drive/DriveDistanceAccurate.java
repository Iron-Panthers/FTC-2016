package org.firstinspires.ftc.team7316.util.commands.drive;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.team7316.util.Loopable;

/**
 * Created by andrew on 10/4/16.
 */
public class DriveDistanceAccurate implements Loopable {

    private double distanceDelta;
    private double distanceRemaining;
    private double startPosition;
    private double wantedPower;
    private double currentPower;
    private DcMotor motor;
    private double changeNegative = 1;
    private final double decreaseRate = 0.93;
    private double decreaseStart = 0.2;
    private final double minPower = 0.1;

    public DriveDistanceAccurate (double distance, double power, DcMotor motor) {
        this.distanceDelta = distance;
        this.wantedPower = Math.abs(power);
        this.decreaseStart *= distance;

        this.motor = motor;

        if (power < 0) {
            this.changeNegative = -1;
        }
    }

    @Override
    public void init() {
        this.currentPower = this.wantedPower;
        this.startPosition = motor.getCurrentPosition();
    }

    @Override
    public void loop() {
        this.distanceRemaining = this.distanceDelta - Math.abs(motor.getCurrentPosition() - this.startPosition);

        if (this.distanceRemaining < this.decreaseStart) {
            if (this.currentPower <= this.minPower) {
                this.currentPower = this.minPower;
            } else {
                this.currentPower *= this.decreaseRate;
            }
        }

        this.motor.setPower((float) this.currentPower * this.changeNegative);
    }

    @Override
    public boolean shouldRemove() {
        return this.distanceRemaining < 0;
    }

    @Override
    public void terminate() {
        this.motor.setPower(0);
    }
}
