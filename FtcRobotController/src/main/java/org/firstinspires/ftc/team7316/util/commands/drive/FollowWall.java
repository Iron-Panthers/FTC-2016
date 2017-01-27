package org.firstinspires.ftc.team7316.util.commands.drive;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.LightSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.team7316.util.Alliance;
import org.firstinspires.ftc.team7316.util.Buffer;
import org.firstinspires.ftc.team7316.util.Loopable;
import org.firstinspires.ftc.team7316.util.hardware.Hardware;
import org.firstinspires.ftc.team7316.util.hardware.SharpIRSensor;

/**
 * Created by andrew on 1/26/17.
 */
public class FollowWall implements Loopable {

    private DcMotor leftMotor;
    private DcMotor rightMotor;

    private SharpIRSensor frontSensor, backSensor;

    private final double distFromWall = 10; //cm
    private final double distBetweenSensors = 40; //cm
    private final double robotWidth = 30; //cm

    private final double p = 0; //ratio of error to turn
    private final double i = 0; //ration of sum of errors to turn
    private final double d = 0; //ratio of delta to turn
    private final double k = 0.01; //power decrease ratio
    private final int bufferSize = 40;

    private double deltaErrorClose = 0;
    private double lastErrorClose = 0;
    private Buffer errorSumClose;

    private double deltaErrorFar = 0;
    private double lastErrorFar = 0;
    private Buffer errorSumFar;

    private double wantedPower;

    public FollowWall (DcMotor leftMotor, DcMotor rightMotor, SharpIRSensor frontSensor, SharpIRSensor backSensor, double wantedPower) {
        this.leftMotor = leftMotor;
        this.rightMotor = rightMotor;
        this.frontSensor = frontSensor;
        this.backSensor = backSensor;
        this.wantedPower = wantedPower;
    }

    @Override
    public void init() {
        this.errorSumClose = new Buffer(this.bufferSize);
        this.deltaErrorClose = 0;
        this.lastErrorClose = 0;

        this.errorSumFar = new Buffer(this.bufferSize);
        this.deltaErrorFar = 0;
        this.lastErrorFar = 0;
    }

    @Override
    public void loop() {
        double errorClose = this.errorClose();
        //this.deltaErrorClose = errorClose - this.lastErrorClose;
        this.errorSumClose.pushValue(errorClose);
        this.lastErrorClose = errorClose;

        double errorFar = this.errorFar();
        //this.deltaErrorFar = errorFar - this.lastErrorFar;
        this.errorSumFar.pushValue(errorFar);
        this.lastErrorFar = errorFar;

        double combinedError = Math.abs(errorClose + errorFar) * k;
        double basePower = wantedPower - combinedError;
        if (basePower < 0) {
            basePower = 0;
        }

        double leftPower = errorClose * p + errorSumClose.sum * i + deltaErrorClose * d + basePower;
        double rightPower = errorFar * p + errorSumFar.sum * i + deltaErrorFar * d + basePower;

        Hardware.log("LeftP: ", leftPower);
        Hardware.log("RightP: ", rightPower);

        Hardware.log("FrontD: ", frontSensor.getDistance(DistanceUnit.CM));
        Hardware.log("BackD", backSensor.getDistance(DistanceUnit.CM));

        Hardware.log("CloseE: ", errorClose);
        Hardware.log("FarE: ", errorFar);

        //this.leftMotor.setPower(leftPower);
        //this.rightMotor.setPower(rightPower);
    }

    @Override
    public boolean shouldRemove() {
        return false;
    }

    @Override
    public void terminate() {
        //this.leftMotor.setPower(0);
        //this.rightMotor.setPower(0);
    }

    public double errorClose() {
        double distance = this.frontSensor.getDistance(DistanceUnit.CM);

        return -(distance - distFromWall);
    }

    public double errorFar() {
        double frontDist = this.frontSensor.getDistance(DistanceUnit.CM);
        double backDist = this.backSensor.getDistance(DistanceUnit.CM);

        double sensorDifference = frontDist - backDist;

        if (sensorDifference > 0) {
            double theta = Math.asin(sensorDifference/distBetweenSensors);
            double alpha = Math.PI/2 - theta;
            double heightToCloseWheel = robotWidth * Math.sin(alpha);
            double distanceToWantedClose = heightToCloseWheel - (distFromWall - frontDist);
            double distanceToWantedFar = robotWidth - distanceToWantedClose;

            return distanceToWantedFar;
        } else {
            double theta = Math.asin(-sensorDifference/distBetweenSensors);
            double alpha = Math.PI/2 - theta;
            double heightToWall = robotWidth * Math.sin(alpha) + frontDist;
            double distanceToWantedFar = heightToWall - (robotWidth + distFromWall);

            return distanceToWantedFar;
        }
    }

}
