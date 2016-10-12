package org.firstinspires.ftc.team7316.util.auto;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.LightSensor;

import org.firstinspires.ftc.team7316.util.Loopable;

/**
 * Created by andrew on 10/4/16.
 */
public class LineFollow implements Loopable {
    private DcMotor leftMotor;
    private boolean leftInverted;

    private DcMotor rightMotor;
    private boolean rightInverted;

    private LightSensor sensor;

    private final double wantedLight = 0.32; //FIX THESE NUMBERS WITH TESTING
    private final double k = 0.1;
    private double wantedPower;
    private double leftPower;
    private double rightPower;

    public LineFollow (DcMotor leftMotor, boolean leftInverted, DcMotor rightMotor, boolean rightInverted, LightSensor sensor, double wantedPower) {
        this.leftMotor = leftMotor;
        this.leftInverted = leftInverted;

        this.rightMotor = rightMotor;
        this.rightInverted = rightInverted;

        this.wantedPower = wantedPower;

        this.sensor = sensor;
    }

    @Override
    public void init() {
        double turn = 0;
        this.turn(turn);
    }

    @Override
    public void loop() {

    }

    @Override
    public boolean shouldRemove() {
        return false;
    }

    @Override
    public void terminate() {
        this.leftMotor.setPower(0);
        this.rightMotor.setPower(0);
    }

    private void turn(double value) { //-1 (left) to 1 (right)
        double leftVal = turnAssist(value);
        double rightVal = turnAssist(-value);

        if (this.leftInverted) {
            leftVal *= -1;
        }
        if (this.rightInverted) {
            rightVal *= -1;
        }

        this.leftMotor.setPower(leftVal);
        this.rightMotor.setPower(rightVal);
    }

    private double turnAssist(double turnAmount) {
        if (turnAmount < 0) {
            return (1 + this.wantedPower)*turnAmount + this.wantedPower;
        } else {
            return (1 - this.wantedPower)*turnAmount + this.wantedPower;
        }
    }

    private double error(double reading) {
        return reading - this.wantedLight;
    }
}
