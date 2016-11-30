package org.firstinspires.ftc.team7316.modes.test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.turn.TurnGyroPID;
import org.firstinspires.ftc.team7316.util.hardware.Hardware;

/**
 * Created by Maxim on 11/29/2016.
 */
@TeleOp(name="turngyrotstmd")
public class TurnGyroTestMode extends OpMode {

    TurnGyroPID command;

    @Override
    public void init() {
        Hardware.setHardwareMap(hardwareMap);
        Hardware.setTelemetry(telemetry);
        command = new TurnGyroPID(Hardware.instance.leftDriveMotor, Hardware.instance.rightDriveMotor, Hardware.instance.gyroSensor, 150);
    }

    @Override
    public void loop() {
        Scheduler.instance.loop();
        Hardware.log("error", command.lastError);
        Hardware.log("sum", command.sumError);
        Hardware.log("delta", command.deltaError);
        Hardware.log("angle", Hardware.instance.gyroSensor.getHeading());

    }
}
