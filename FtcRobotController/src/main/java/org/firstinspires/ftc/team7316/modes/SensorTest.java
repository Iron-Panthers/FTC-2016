package org.firstinspires.ftc.team7316.modes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.LightSensor;

import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.auto.conditions.RedThreshold;
import org.firstinspires.ftc.team7316.util.hardware.ColorWrapper;
import org.firstinspires.ftc.team7316.util.hardware.DcMotorWrapper;
import org.firstinspires.ftc.team7316.util.hardware.GyroWrapper;
import org.firstinspires.ftc.team7316.util.hardware.Hardware;
import org.firstinspires.ftc.team7316.util.input.GamepadWrapper;

/**
 * Created by andrew on 10/11/16.
 */
@TeleOp(name = "SensorTest")
public class SensorTest extends OpMode {

    ColorSensor colorSensor;
    GamepadWrapper gamepadWrapper;
    RedThreshold threshold;

    @Override
    public void init() {
        gamepadWrapper = new GamepadWrapper(gamepad1);

        colorSensor = hardwareMap.colorSensor.get("color");

        threshold = new RedThreshold(0.5, colorSensor, true);
        colorSensor.enableLed(false);
    }

    @Override
    public void loop() {
        Scheduler.instance.loop();

        telemetry.addData("touch", Hardware.instance.touchSensor.isPressed());
    }
}
