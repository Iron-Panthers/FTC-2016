package org.firstinspires.ftc.team7316.modes.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.AutoCodes;
import org.firstinspires.ftc.team7316.util.hardware.Hardware;

/**
 * Created by andrew on 2/3/17.
 */
@Autonomous(name = "B 2b")
public class BlueDoubleBeacon extends OpMode {
    @Override
    public void init() {
        Scheduler.instance.clear();

        Hardware.setHardwareMap(hardwareMap);
        Hardware.setTelemetry(telemetry);

        Scheduler.instance.addTask(AutoCodes.blueDoubleBeacon());
    }

    @Override
    public void loop() {
        Scheduler.instance.loop();
    }
}
