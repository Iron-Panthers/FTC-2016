package org.firstinspires.ftc.team7316.modes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.team7316.util.hardware.Hardware;

/**
 * Created by Maxim on 10/19/2016.
 */
@TeleOp(name = "Motor Test")
public class MotorTest extends OpMode {

    private DcMotor motor;

    @Override
    public void init() {
        motor = hardwareMap.dcMotor.get("motor");
    }

    @Override
    public void loop() {
        motor.setPower(gamepad1.right_stick_y);
        telemetry.addData("encoder", motor.getCurrentPosition());
    }
}
