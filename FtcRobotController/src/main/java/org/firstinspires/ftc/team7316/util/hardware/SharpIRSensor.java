package org.firstinspires.ftc.team7316.util.hardware;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.AnalogInputController;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareDevice;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

/**
 * Created by andrew on 1/26/17.
 */
public class SharpIRSensor implements DistanceSensor {

    /*
    private final static double a = 54.5;
    private final static double b = 0.488;
    private final static double c = 0;
    */

    private final double a, b, c;

    private AnalogInput irSensor;

    /**
     *
     * @param irSensor the sensor
     * @param a exponential coefficient
     * @param b exponential base
     * @param c asymptote
     */
    public SharpIRSensor(AnalogInput irSensor, double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.irSensor = irSensor;
    }

    @Override
    public double getDistance(DistanceUnit unit) {
        double voltage = this.irSensor.getVoltage();
        double distance = a * Math.pow(b, voltage) + c;

        return distance;
    }

    @Override
    public Manufacturer getManufacturer() {
        return this.irSensor.getManufacturer();
    }

    @Override
    public String getDeviceName() {
        return this.irSensor.getDeviceName();
    }

    @Override
    public String getConnectionInfo() {
        return this.irSensor.getConnectionInfo();
    }

    @Override
    public int getVersion() {
        return this.irSensor.getVersion();
    }

    @Override
    public void resetDeviceConfigurationForOpMode() {
        this.irSensor.resetDeviceConfigurationForOpMode();
    }

    @Override
    public void close() {
        this.irSensor.close();
    }
}
/*
5   3.00
10  2.65
15  1.80
20  1.40
25  1.07
 */
