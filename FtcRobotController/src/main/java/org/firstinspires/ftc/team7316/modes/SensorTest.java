package org.firstinspires.ftc.team7316.modes;

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

    ColorSensor colorSensor;
    GamepadWrapper gamepadWrapper;
    OpticalDistanceSensor ods;

    @Override
    public void init() {
        //gamepadWrapper = new GamepadWrapper(gamepad1);

        colorSensor = hardwareMap.colorSensor.get("color");
        ods = hardwareMap.opticalDistanceSensor.get("light");
    }

    @Override
    public void loop() {
        Scheduler.instance.loop();

        telemetry.addData("red", colorSensor.red());
        telemetry.addData("green", colorSensor.green());
        telemetry.addData("blue", colorSensor.blue());
        telemetry.addData("ods", ods.getLightDetected());
    }
}
