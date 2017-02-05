package org.firstinspires.ftc.team7316.modes.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.AutoCodes;
import org.firstinspires.ftc.team7316.util.hardware.Hardware;

/**
 * Created by andrew on 2/4/17.
 */

@Autonomous(name = "Blue Double Double No Park")

public class BlueDoubleDoubleNoPark extends OpMode {

    @Override
    public void init() {
        Scheduler.instance.clear();

        Hardware.setHardwareMap(hardwareMap);
        Hardware.setTelemetry(telemetry);

        Scheduler.instance.addTask(AutoCodes.blueDoubleBeaconDoubleShoot(false));
    }

    @Override
    public void loop() {
        Scheduler.instance.loop();
    }

}
