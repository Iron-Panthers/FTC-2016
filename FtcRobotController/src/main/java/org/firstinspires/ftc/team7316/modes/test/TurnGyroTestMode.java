package org.firstinspires.ftc.team7316.modes.test;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.turn.TurnGyroPID;
import org.firstinspires.ftc.team7316.util.hardware.Hardware;

/**
 * Created by Maxim on 11/29/2016.
 */
@Disabled
@TeleOp(name="TurnGyroTest")
public class TurnGyroTestMode extends OpMode {

    TurnGyroPID command;

    @Override
    public void init() {
        Hardware.setHardwareMap(hardwareMap);
        Hardware.setTelemetry(telemetry);
        command = new TurnGyroPID(Hardware.instance.leftDriveMotor, Hardware.instance.rightDriveMotor, Hardware.instance.gyroSensor, -90);
        Scheduler.instance.addTask(command);
    }

    @Override
    public void loop() {
        Scheduler.instance.loop();
        Hardware.log("error", command.lastError);
        Hardware.log("power", command.power);
        Hardware.log("angle", Hardware.instance.gyroSensor.getHeading());

    }
}
