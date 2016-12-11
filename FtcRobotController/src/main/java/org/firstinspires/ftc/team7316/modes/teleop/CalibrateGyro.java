package org.firstinspires.ftc.team7316.modes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.team7316.util.hardware.Hardware;

/**
 * Created by Maxim on 11/29/2016.
 */
@TeleOp(name = "Calibrate Gyro")
public class CalibrateGyro extends OpMode {
    @Override
    public void init() {
        Hardware.setHardwareMap(this.hardwareMap);
        Hardware.instance.gyroSensor.calibrate();
    }

    @Override
    public void loop() {
        telemetry.addData("Message", "Gyro calibrated.");
    }
}
