package org.firstinspires.ftc.team7316.util.hardware;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.AnalogInputController;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareDevice;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.team7316.util.Buffer;
import org.firstinspires.ftc.team7316.util.Loopable;

/**
 * Created by andrew on 1/26/17.
 */
public class SharpIRSensor implements DistanceSensor, Loopable {

    /*
    private final static double a = 54.5;
    private final static double b = 0.488;
    private final static double c = 0;
    */

    private final double a, h, k;

    private AnalogInput irSensor;
    private Buffer buffer;

    public SharpIRSensor(AnalogInput irSensor, double a, double h, double k) {
        this.irSensor = irSensor;
        this.k = k;
        this.h = h;
        this.a = a;
    }

    @Override
    public double getDistance(DistanceUnit unit) {
        double voltage = this.irSensor.getVoltage();
        double distance = a / Math.sqrt(voltage - h) + k;

        return unit.fromCm(distance);
    }

    public double getVoltage() {
        return buffer.average();
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

    @Override
    public void init() {
        this.buffer = new Buffer(50);
    }

    @Override
    public void loop() {
        buffer.pushValue(irSensor.getVoltage());
    }

    @Override
    public boolean shouldRemove() {
        return false;
    }

    @Override
    public void terminate() {

    }
}
/*
5   3.00
10  2.65
15  1.80
20  1.40
25  1.07
 */
