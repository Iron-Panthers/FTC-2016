package org.firstinspires.ftc.team7316.modes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.hardware.Hardware;

/**
 * Created by wayne on 10/11/16.
 */
@Disabled
@TeleOp(name = "SensorTest")
public class SensorTest extends OpMode {

    @Override
    public void init() {
        Hardware.setHardwareMap(hardwareMap);
        Hardware.setTelemetry(telemetry);
    }

    @Override
    public void loop() {
        Scheduler.instance.loop();

        Hardware.log("left encoder", Hardware.instance.leftDriveMotor.getCurrentPosition());
        Hardware.log("right encoder", Hardware.instance.rightDriveMotor.getCurrentPosition());
        Hardware.log("floor ods", Hardware.instance.lightSensorFront.getLightDetected());
    }
}
