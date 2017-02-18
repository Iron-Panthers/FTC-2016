package org.firstinspires.ftc.team7316.modes.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.team7316.util.Alliance;
import org.firstinspires.ftc.team7316.util.LateralDirection;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.AutoCodes;
import org.firstinspires.ftc.team7316.util.commands.turn.TurnDoubleSensor;
import org.firstinspires.ftc.team7316.util.hardware.Hardware;

/**
 * Created by andrew on 2/10/17.
 */

@Disabled
@Autonomous(name="Double Sensor Turn")
public class TestLineDouble extends OpMode {
    @Override
    public void init() {
        Scheduler.instance.clear();

        Hardware.setHardwareMap(hardwareMap);
        Hardware.setTelemetry(telemetry);

        Scheduler.instance.addTask(new TurnDoubleSensor(Hardware.instance.leftDriveMotor, Hardware.instance.rightDriveMotor, Hardware.instance.lightSensorLeft, Hardware.instance.lightSensorRight, LateralDirection.LEFT));
    }

    @Override
    public void loop() {
        Scheduler.instance.loop();
    }
}
