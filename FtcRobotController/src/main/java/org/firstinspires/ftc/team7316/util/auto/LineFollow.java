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

    private double errorToLeftRatio = 0;
    private double errorToRightRatio = 0;

    private final double k = 0.2; //ratio of error to turn
    private double wantedPower;

    private double minPower = 0; //-1 to maxPower
    private double maxPower = 1; //minPower to 1

    public LineFollow (DcMotor leftMotor, DcMotor rightMotor, LightSensor sensor, double wantedPower) {
        sensor.enableLed(true);

        this.leftMotor = leftMotor;
        this.rightMotor = rightMotor;

        this.wantedPower = wantedPower;

        this.sensor = sensor;

        double minError = this.error(minLight);
        double maxError = this.error(maxLight);

        double minErrorSlope = (maxPower - wantedPower)/minError;
        double maxErrorSlope = (minPower - wantedPower)/maxError;

        errorToLeftRatio = (minErrorSlope + maxErrorSlope)/2;

        minErrorSlope = (minPower - wantedPower)/minError;
        maxErrorSlope = (maxPower - wantedPower)/maxError;

        errorToRightRatio = (minErrorSlope + maxErrorSlope)/2;

    }

    @Override
    public void init() {
    }

    @Override
    public void loop() {
        this.leftMotor.setPower(leftPower(this.sensor.getLightDetected()));
        this.rightMotor.setPower(rightPower(this.sensor.getLightDetected()));
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

    private double leftPower(double reading) {
        /*
        1. convert reading to error
        2. convert error to motor power
            a. motor power should be in range 0 to 1 (later replace min and max motor power)
            b. if error is minLight should return 0 motor
            c. if error is wantedLight should return wantedPower
            d. if error is maxLight should return 1 motor power
         */
        double error = error(reading);
        return error * errorToLeftRatio + wantedPower;
    }

    private double rightPower(double reading) {
        double error = error(reading);
        return error * errorToRightRatio + wantedPower;
    }

    private double error(double reading) {
        return reading - this.wantedLight;
    }
}
