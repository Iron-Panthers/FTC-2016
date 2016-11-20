package org.firstinspires.ftc.team7316.util.commands;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.LightSensor;

import org.firstinspires.ftc.team7316.util.Loopable;
import org.firstinspires.ftc.team7316.util.hardware.Hardware;

/**
 * Created by andrew on 10/4/16.
 */
public class LineFollow implements Loopable {
    private DcMotor leftMotor;
    private DcMotor rightMotor;

    private LightSensor sensor;

    private final double wantedLight = 0.2; //FIX THESE NUMBERS WITH TESTING
    private final double minLight = 0.04;
    private final double maxLight = 0.4;

    private double errorToLeftRatio = 0;
    private double errorToRightRatio = 0;

    private int counter = 0;
    private final double p = 1.2; //ratio of error to turn
    private final double i = 0; //ration of sum of errors to turn
    private final double d = 3.5; //ratio of delta to turn
    private double deltaError = 0;
    private double lastError = 0;
    private double errorSum = 0;
    private double wantedPower;

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
        double error = error(this.sensor.getLightDetected());

        this.leftMotor.setPower(leftPower(error));
        this.rightMotor.setPower(rightPower(error));
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

    private double leftPower(double error) {
        /*
        1. convert reading to error
        2. convert error to motor power
            a. motor power should be in range 0 to 1 (later replace min and max motor power)
            b. if error is minLight should return 0 motor
            c. if error is wantedLight should return wantedPower
            d. if error is maxLight should return 1 motor power
         */
        return error * errorToLeftRatio * this.p + this.errorSum * errorToLeftRatio * this.i + this.deltaError * errorToLeftRatio * this.d + wantedPower;
    }

    private double rightPower(double error) {
        return error * errorToRightRatio * this.p + this.errorSum * errorToRightRatio * this.i + this.deltaError * errorToRightRatio * this.d + wantedPower;
    }

    private double error(double reading) {
        double error = reading - this.wantedLight;

        this.errorSum += error;

        if (counter % 3 == 0) {
            this.deltaError = 0;
        }
        this.deltaError += error - this.lastError;
        counter++;

        Hardware.instance.jankDelta = this.deltaError;
        Hardware.instance.jankSum = this.errorSum;

        lastError = error;

        return error;
    }
}
