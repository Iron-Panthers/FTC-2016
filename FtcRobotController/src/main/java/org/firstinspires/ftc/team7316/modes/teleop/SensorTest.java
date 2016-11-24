package org.firstinspires.ftc.team7316.modes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.LightSensor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;

import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.hardware.ColorWrapper;
import org.firstinspires.ftc.team7316.util.hardware.DcMotorWrapper;
import org.firstinspires.ftc.team7316.util.hardware.GyroWrapper;
import org.firstinspires.ftc.team7316.util.hardware.Hardware;
import org.firstinspires.ftc.team7316.util.input.GamepadWrapper;

/**
 * Created by wayne on 10/11/16.
 */
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
        Hardware.log("cat ods", Hardware.instance.catapultSensor.getLightDetected());
    }
}
