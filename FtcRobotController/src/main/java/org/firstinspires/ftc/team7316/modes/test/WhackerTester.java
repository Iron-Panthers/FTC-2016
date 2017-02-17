package org.firstinspires.ftc.team7316.modes.test;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.team7316.modes.BaseOpMode;
import org.firstinspires.ftc.team7316.util.hardware.Hardware;

/**
 * Created by Maxim on 2/16/2017.
 */
@TeleOp( name = "WhackerTester")
public class WhackerTester extends BaseOpMode {
    @Override
    public void onInit() {

    }

    @Override
    public void onLoop() {
        Hardware.instance.capBallWhackerMotor.setPower(gamepad1.left_stick_y);

        Hardware.log("right", Hardware.instance.whackerRight.isPressed());
        Hardware.log("left", Hardware.instance.whackerLeft.isPressed());
    }
}
