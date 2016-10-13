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

    LightSensor lightSensor;
    GamepadWrapper gamepadWrapper;

    @Override
    public void init() {
        gamepadWrapper = new GamepadWrapper(gamepad1);

        Hardware.instance.setHardwareMap(hardwareMap);

        lightSensor = Hardware.instance.lightSensor;
    }

    @Override
    public void loop() {
        Scheduler.instance.loop();

        Hardware.instance.leftDriveMotor.setPower(gamepadWrapper.left_stick.getY());
        Hardware.instance.rightDriveMotor.setPower(gamepadWrapper.right_stick.getY());

        telemetry.addData("light: ", lightSensor.getLightDetected());
        telemetry.addData("raw_light: ", lightSensor.getRawLightDetected());
    }
}
