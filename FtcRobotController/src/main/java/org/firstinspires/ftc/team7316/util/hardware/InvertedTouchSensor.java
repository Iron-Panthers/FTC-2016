package org.firstinspires.ftc.team7316.util.hardware;

import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.TouchSensor;

/**
 * Temporary solution to a temporary problem
 */
public class InvertedTouchSensor implements TouchSensor {

    TouchSensor sensor;

    public InvertedTouchSensor(TouchSensor sensor) {
        this.sensor = sensor;
    }

    @Override
    public double getValue() {
        return sensor.getValue();
    }

    @Override
    public boolean isPressed() {
        return !sensor.isPressed();
    }

    @Override
    public Manufacturer getManufacturer() {
        return sensor.getManufacturer();
    }

    @Override
    public String getDeviceName() {
        return sensor.getDeviceName();
    }

    @Override
    public String getConnectionInfo() {
        return sensor.getConnectionInfo();
    }

    @Override
    public int getVersion() {
        return sensor.getVersion();
    }

    @Override
    public void resetDeviceConfigurationForOpMode() {
        sensor.resetDeviceConfigurationForOpMode();
    }

    @Override
    public void close() {
        sensor.close();
    }
}
