package org.firstinspires.ftc.team7316.modes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.AutoCodes;
import org.firstinspires.ftc.team7316.util.commands.LineFollow;
import org.firstinspires.ftc.team7316.util.commands.TurnGyro;
import org.firstinspires.ftc.team7316.util.hardware.Hardware;

/**
 * Created by wayne on 9/27/16.
*/

@Autonomous(name = "Double Shoot and Beacon")
public class AutoMode extends OpMode {

    @Override
    public void init() {
        Scheduler.instance.clear();

        Hardware.setHardwareMap(hardwareMap);
        Hardware.setTelemetry(telemetry);

        Scheduler.instance.addTask(AutoCodes.doubleShootAndBeaconFromRed());
    }

    @Override
    public void loop() {
        Scheduler.instance.loop();
        Hardware.log(Hardware.tag, "Left Motor: " + Hardware.instance.leftDriveMotor.getCurrentPosition());
        Hardware.log(Hardware.tag, "Right Motor: " + Hardware.instance.rightDriveMotor.getCurrentPosition());
        Hardware.log(Hardware.tag, "gyro: " + Hardware.instance.gyroSensor.getHeading());
    }
}

