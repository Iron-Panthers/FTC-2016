package org.firstinspires.ftc.team7316.modes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.LightSensor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;

import org.firstinspires.ftc.team7316.util.Buffer;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.hardware.ColorWrapper;
import org.firstinspires.ftc.team7316.util.hardware.DcMotorWrapper;
import org.firstinspires.ftc.team7316.util.hardware.GyroWrapper;
import org.firstinspires.ftc.team7316.util.hardware.Hardware;
import org.firstinspires.ftc.team7316.util.input.GamepadWrapper;

/**
 * Created by wayne on 10/11/16.
 */
@TeleOp(name = "TestIr")
public class TestIrSensors extends OpMode {

    Buffer front, back;

    @Override
    public void init() {
        Hardware.setHardwareMap(hardwareMap);
        Hardware.setTelemetry(telemetry);
        front = new Buffer(30);
        back = new Buffer(30);
    }

    @Override
    public void loop() {
        Scheduler.instance.loop();

        front.pushValue(Hardware.instance.frontSideInfaredSensor.getVoltage());
        back.pushValue(Hardware.instance.backSideInfaredSensor.getVoltage());

        Hardware.log("front sensor", front.sum);
        Hardware.log("back sensor", back.sum);
    }
}
