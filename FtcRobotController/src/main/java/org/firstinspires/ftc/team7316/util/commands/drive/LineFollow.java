package org.firstinspires.ftc.team7316.util.commands.drive;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.LightSensor;
import com.vuforia.EyewearUserCalibrator;

import org.firstinspires.ftc.team7316.util.Alliance;
import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Loopable;
import org.firstinspires.ftc.team7316.util.hardware.Hardware;

/**
 * Created by andrew on 10/4/16.
 */
public class LineFollow implements Loopable {
    private DcMotor leftMotor;
    private DcMotor rightMotor;

    private int invert = 1;

    private boolean boosted = false;

    private LightSensor sensor;

    private double errorToLeftRatio = 0;
    private double errorToRightRatio = 0;

    private int counter = 0;
    private double p = 0.4; //ratio of error to turn
    private double i = 0; //ration of sum of errors to turn
    private double d = 0.5; //ratio of delta to turn
    private double deltaError = 0;
    private double lastError = 0;
    private double errorSum = 0;
    private int sumCounts = 0;
    private double wantedPower;

    private double minPower = -0.45; //-1 to maxPower
    private double maxPower = 0.35; //minPower to 1

    public LineFollow (DcMotor leftMotor, DcMotor rightMotor, LightSensor sensor, double wantedPower) {
        this(leftMotor, rightMotor, sensor, wantedPower, Alliance.BLUE);
    }

    public LineFollow (DcMotor leftMotor, DcMotor rightMotor, LightSensor sensor, double wantedPower, Alliance color) {

        this.sensor = sensor;

        this.leftMotor = leftMotor;
        this.rightMotor = rightMotor;

        this.wantedPower = wantedPower;

        if (color == Alliance.RED) {
            this.invert = -1;
        }

    }


    public LineFollow (DcMotor leftMotor, DcMotor rightMotor, LightSensor sensor, double wantedPower, Alliance color, double p, double i, double d, double maxPower, double minPower) {
        this(leftMotor, rightMotor, sensor, wantedPower);
        this.p = p;
        this.i = i;
        this.d = d;
        this.maxPower = maxPower;
        this.minPower = minPower;
    }

    public LineFollow (DcMotor leftMotor, DcMotor rightMotor, LightSensor sensor, double wantedPower, Alliance color, double p, double i, double d, double maxPower, double minPower, boolean turnRight) {
        this(leftMotor, rightMotor, sensor, wantedPower);
        this.p = p;
        this.i = i;
        this.d = d;
        this.maxPower = maxPower;
        this.minPower = minPower;
        this.boosted = true;

        if (turnRight) {
            this.leftMotor = rightMotor;
            this.rightMotor = leftMotor;
        }
    }

    @Override
    public void init() {

        sensor.enableLed(true);

        double minError = this.error(Constants.MIN_LIGHT);
        double maxError = this.error(Constants.MAX_LIGHT);
        double minErrorSlope = (maxPower - wantedPower)/minError;
        double maxErrorSlope = (minPower - wantedPower)/maxError;

        errorToLeftRatio = (minErrorSlope + maxErrorSlope)/2;

        minErrorSlope = (minPower - wantedPower)/minError;
        maxErrorSlope = (maxPower - wantedPower)/maxError;

        errorToRightRatio = (minErrorSlope + maxErrorSlope)/2;

    }

    @Override
    public void loop() {
        double error = error(this.sensor.getLightDetected());

        if (invert == 1) {

            this.leftMotor.setPower(leftPower(error));
            this.rightMotor.setPower(rightPower(error));

        } else {

            this.leftMotor.setPower(rightPower(error));
            this.rightMotor.setPower(leftPower(error));

        }
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
        return (error * errorToLeftRatio * this.p + this.errorSum * errorToLeftRatio * this.i + this.deltaError * errorToLeftRatio * this.d + wantedPower) * (this.invert == -1 ? 1.5 : 1);
    }

    private double rightPower(double error) {
        return error * errorToRightRatio * this.p + (this.errorSum/this.sumCounts) * errorToRightRatio * this.i + this.deltaError * errorToRightRatio * this.d + wantedPower;
    }

    private double error(double reading) {
        double error = reading - Constants.WANTED_LIGHT;

        this.errorSum += error;
        this.sumCounts++;

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
