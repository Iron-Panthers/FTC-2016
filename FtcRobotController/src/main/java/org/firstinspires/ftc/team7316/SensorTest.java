package org.firstinspires.ftc.team7316;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.LightSensor;

import org.firstinspires.ftc.team7316.util.Scheduler;
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

    @Override
    public void init() {
        gamepadWrapper = new GamepadWrapper(gamepad1);

        colorSensor = hardwareMap.colorSensor.get("color");
    }

    @Override
    public void loop() {
        Scheduler.instance.loop();

        telemetry.addData("red", colorSensor.red());
        telemetry.addData("green", colorSensor.green());
        telemetry.addData("blue", colorSensor.blue());
        telemetry.addData("alpha", colorSensor.alpha());
        telemetry.addData("argb", Integer.toHexString(colorSensor.argb()));
    }
}
