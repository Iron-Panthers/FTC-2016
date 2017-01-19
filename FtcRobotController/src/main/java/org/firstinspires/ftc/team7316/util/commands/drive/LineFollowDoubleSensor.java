package org.firstinspires.ftc.team7316.util.commands.drive;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.LightSensor;

import org.firstinspires.ftc.team7316.util.Alliance;
import org.firstinspires.ftc.team7316.util.Loopable;
import org.firstinspires.ftc.team7316.util.hardware.Hardware;

/**
 * Created by andrew on 1/13/17.
 */
public class LineFollowDoubleSensor implements Loopable {

    enum State {
        CLAMPED,
        NOTCLAMPED
    }

    private State state = State.NOTCLAMPED;

    private LightSensor leftLightSensor;
    private LightSensor rightLightSensor;

    private DcMotor leftMotor;
    private DcMotor rightMotor;

    private int counter = 0;
    private double p = 0; //ratio of error to turn
    private double i = 0; //ration of sum of errors to turn
    private double d = 0; //ratio of delta to turn
    private double deltaError = 0;
    private double lastError = 0;
    private double errorSum = 0;
    private int sumCounts = 0;

    private final double clampedThreshold = 0.2; //both sensors must be higher than this value
    private final double unclampedThreshold = 1.5; //this is based on the sum of the sensors

    private final double clampedP = 0.6;
    private final double clampedI = 0;
    private final double clampedD = 1;

    private final double unclampedP = 0.6;
    private final double unclampedI = 0;
    private final double unclampedD = 2;

    private double wantedPower;

    private final double wantedSensorSum = 0.5;

    public LineFollowDoubleSensor (DcMotor leftMotor, DcMotor rightMotor, LightSensor leftSensor,  LightSensor rightSensor, double wantedPower) {
        this(leftMotor, rightMotor, leftSensor, rightSensor, wantedPower, Alliance.BLUE);
    }

    public LineFollowDoubleSensor (DcMotor leftMotor, DcMotor rightMotor, LightSensor leftSensor, LightSensor rightSensor, double wantedPower, Alliance color) {

        this.leftLightSensor = leftSensor;
        this.rightLightSensor = rightSensor;

        this.leftMotor = leftMotor;
        this.rightMotor = rightMotor;

        this.wantedPower = wantedPower;
    }

    @Override
    public void init() {
        state = State.NOTCLAMPED;

        this.deltaError = 0;
        this.lastError = 0;
        this.errorSum = 0;
        this.counter = 0;
        this.sumCounts = 0;

        leftLightSensor.enableLed(true);
        rightLightSensor.enableLed(true);
    }

    @Override
    public void loop() {
        double error = 0;

        Hardware.log(Hardware.tag, "Left: " + leftLightSensor.getLightDetected());
        Hardware.log(Hardware.tag, "Right: " + rightLightSensor.getLightDetected());

        //check which state we are in
        if (state == State.NOTCLAMPED) { //this state will use the difference in the sensors as the error
            if (leftLightSensor.getLightDetected() > this.clampedThreshold && rightLightSensor.getLightDetected() > this.clampedThreshold) { //both sensors must be higher than a value WAYNE DID THIS
                state = State.CLAMPED;
                resetPID();
                error = unclampedError();
            } else {
                error = clampedError();
            }

        } else if (state == State.CLAMPED) {
            if (error > this.clampedThreshold) { //the combined error must be higher than a threshold, then we are off the line WAYNE IS SO COOL
                state = State.NOTCLAMPED;
                resetPID();
                error = clampedError();
            } else {
                error = unclampedError();
            }
        }

        //pid engine
        double leftPower = p * error + i * errorSum + d * deltaError;
        double rightPower = p * error + i * errorSum + d * deltaError;

        //I PROBABLY DID THIS ALL WRONG - <3 Wayne
        this.errorSum += error;

        if (counter % 3 == 0) {
            this.deltaError = 0;
        }
        this.deltaError += error - this.lastError;
        counter++;
        lastError = error;
    }

    @Override
    public boolean shouldRemove() {
        return false;
    }

    @Override
    public void terminate() {
        leftMotor.setPower(0);
        rightMotor.setPower(0);
        leftLightSensor.enableLed(false);
        rightLightSensor.enableLed(false);
    }

    public double clampedError() {
        double leftVal = invertSensor(leftLightSensor.getLightDetected());
        double rightVal = invertSensor(rightLightSensor.getLightDetected());

        return rightVal - leftVal; //1 means turn right, -1 means turn left
    }

    public double unclampedError() {
        double leftVal = invertSensor(leftLightSensor.getLightDetected()); //THIS CODE WILL NOT WORK IF THE LIGHT SENSORS ARE TOO FAR APART
        double rightVal = invertSensor(rightLightSensor.getLightDetected());

        return leftVal + rightVal - wantedSensorSum;
    }

    public double invertSensor(double sensorValue) { //assuming sensor goes from 0 to 1
        return 1 - sensorValue;
    }

    public void resetPID() {
        this.deltaError = 0;
        this.lastError = 0;
        this.errorSum = 0;
        this.counter = 0;
        this.sumCounts = 0;

        if (state == State.NOTCLAMPED) {
            this.p = this.unclampedP;
            this.i = this.unclampedI;
            this.d = this.unclampedD;
        } else if (state == State.CLAMPED) {
            this.p = this.clampedP;
            this.i = this.clampedI;
            this.d = this.clampedD;
        }
    }
}
