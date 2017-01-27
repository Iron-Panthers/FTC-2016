package org.firstinspires.ftc.team7316.modes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.LightSensor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.team7316.util.Buffer;
import org.firstinspires.ftc.team7316.util.GatedBuffer;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.drive.FollowWall;
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

    private FollowWall cmd;

    @Override
    public void init() {
        Hardware.setHardwareMap(hardwareMap);
        Hardware.setTelemetry(telemetry);

        cmd = new FollowWall(Hardware.instance.leftDriveMotor, Hardware.instance.rightDriveMotor, Hardware.instance.frontSideInfaredSensor, Hardware.instance.backSideInfaredSensor, 0.3);
        Scheduler.instance.addTask(cmd);
    }

    @Override
    public void loop() {
        Scheduler.instance.loop();
    }
}
