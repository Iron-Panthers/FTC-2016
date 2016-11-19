package org.firstinspires.ftc.team7316.modes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;

import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.CommandSequence;
import org.firstinspires.ftc.team7316.util.commands.LineFollow;
import org.firstinspires.ftc.team7316.util.commands.LineFollowUntilCondition;
import org.firstinspires.ftc.team7316.util.commands.TurnGyro;
import org.firstinspires.ftc.team7316.util.hardware.Hardware;

/**
 * Created by andrew on 9/27/16.
 */

@Autonomous(name = "PantherAuto")
public class AutoMode extends OpMode {

    private DcMotor leftMotor, rightMotor;
    private OpticalDistanceSensor ods;

    @Override
    public void init() {
        Scheduler.instance.clear();

        Hardware.setHardwareMap(hardwareMap);
        Hardware.setTelemetry(telemetry);

        leftMotor = Hardware.instance.leftDriveMotor;
        rightMotor = Hardware.instance.rightDriveMotor;
        ods = Hardware.instance.lightSensor;

        Scheduler.instance.addTask(new LineFollow(leftMotor, rightMotor, ods, 0.2));
    }

    @Override
    public void loop() {
        Scheduler.instance.loop();
        Hardware.log(Hardware.tag, "Left Motor: " + Hardware.instance.leftDriveMotor.getCurrentPosition());
        Hardware.log(Hardware.tag, "Right Motor: " + Hardware.instance.rightDriveMotor.getCurrentPosition());
        Hardware.log(Hardware.tag, "gyro: " + Hardware.instance.gyroSensor.getHeading());
        Hardware.log(Hardware.tag, "ultrasonic: " + Hardware.instance.distanceSensor.getUltrasonicLevel());
    }
}

