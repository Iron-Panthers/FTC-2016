package org.firstinspires.ftc.team7316.modes.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.AutoCodes;
import org.firstinspires.ftc.team7316.util.hardware.Hardware;

/**
 * Created by andrew on 11/20/16.
 */


@Autonomous(name = "R 2s 2b")
public class RedDoubleShootDoubleBeaconNoPark extends OpMode {
    @Override
    public void init() {
        Scheduler.instance.clear();

        Hardware.setHardwareMap(hardwareMap);
        Hardware.setTelemetry(telemetry);

        Scheduler.instance.addTask(AutoCodes.redDoubleShootDoubleBeacon());
    }

    @Override
    public void loop() {
        Hardware.log("coder ", Hardware.instance.leftDriveMotor.getCurrentPosition());
        Scheduler.instance.loop();
    }
}
