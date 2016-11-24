package org.firstinspires.ftc.team7316.modes.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.AutoCodes;
import org.firstinspires.ftc.team7316.util.hardware.Hardware;

/**
 * Created by andrew on 11/20/16.
 */

@Autonomous(name = "TestAuto")
public class TestAuto extends OpMode {
    @Override
    public void init() {
        Scheduler.instance.clear();

        Hardware.setHardwareMap(hardwareMap);
        Hardware.setTelemetry(telemetry);

        //Scheduler.instance.addTask(AutoCodes.robotDriveDistanceAccurate(Constants.distanceToTicks(5.5), 0.3));
    }

    @Override
    public void loop() {
        Scheduler.instance.loop();
        Hardware.instance.leftDriveMotor.setPower(0.5);
        Hardware.instance.rightDriveMotor.setPower(0.5);

        Hardware.log(Hardware.tag, "Left Motor: " + Hardware.instance.leftDriveMotor.getCurrentPosition());
        Hardware.log(Hardware.tag, "Right Motor: " + Hardware.instance.rightDriveMotor.getCurrentPosition());
        Hardware.log(Hardware.tag, "gyro: " + Hardware.instance.gyroSensor.getHeading());
        Hardware.log(Hardware.tag, "7 ft to ticks" + Constants.distanceToTicks(5.5));
    }
}
