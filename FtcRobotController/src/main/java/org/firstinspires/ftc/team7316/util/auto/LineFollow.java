package org.firstinspires.ftc.team7316.util.auto;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.LightSensor;

import org.firstinspires.ftc.team7316.util.Loopable;

/**
 * Created by andrew on 10/4/16.
 */
public class LineFollow implements Loopable {
    private DcMotor leftMotor;
    private DcMotor rightMotor;

    private LightSensor sensor;

    private final double wantedLight = 0.387; //FIX THESE NUMBERS WITH TESTING
    private final double minLight = 0.31;
    private final double maxLight = 0.46;

    private double errorToTurnRatio = 0;

    private final double k = 1; //ratio of error to turn
    private double wantedPower;
    private double leftPower;
    private double rightPower;

    private double minPower = 0; //-1 to maxPower
    private double maxPower = 0.3; //minPower to 1

    public LineFollow (DcMotor leftMotor, DcMotor rightMotor, LightSensor sensor, double wantedPower) {
        sensor.enableLed(true);

        this.leftMotor = leftMotor;
        this.rightMotor = rightMotor;

        this.wantedPower = wantedPower;

        this.sensor = sensor;

        double minError = this.error(minLight);
        double maxError = this.error(maxLight);

        double minErrorSlope = 1/minError;
        double maxErrorSlope = -1/maxError;

        errorToTurnRatio = (minErrorSlope + maxErrorSlope)/2;
    }

    @Override
    public void init() {
        double turn = 0;
        this.turn(turn);
    }

    @Override
    public void loop() {
        double error = this.error(this.sensor.getLightDetected());
        double turnNumber = error*errorToTurnRatio;

        this.turn(turnNumber*k);
    }

    @Override
    public boolean shouldRemove() {
        return false;
    }

    @Override
    public void terminate() {
        this.leftMotor.setPower(0);
        this.rightMotor.setPower(0);
        sensor.enableLed(false);
    }

    private void turn(double value) { //-1 (left) to 1 (right)
        if (value > 1) {
            value = 1;
        } else if (value < -1) {
            value = -1;
        }

        double leftVal = turnAssist(value);
        double rightVal = turnAssist(-value);

        this.leftMotor.setPower(leftVal);
        this.rightMotor.setPower(rightVal);
    }

    private double turnAssist(double turnAmount) {
        if (turnAmount < 0) {
            return (this.wantedPower - minPower)*turnAmount + this.wantedPower;
        } else {
            return (maxPower - this.wantedPower)*turnAmount + this.wantedPower;
        }
    }

    private double error(double reading) {
        return reading - this.wantedLight;
    }
}
