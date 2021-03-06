package org.firstinspires.ftc.team7316.modes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.input.ButtonListener;
import org.firstinspires.ftc.team7316.util.input.GamepadWrapper;

/**
 * Created by Maxim on 10/18/2016.
 */
@TeleOp(name="Servo Test")
public class ServoTest extends OpMode {

    private static final double increments = 0.05;
    double leftpos, rightpos;
    Servo left, right;
    GamepadWrapper gp;

    @Override
    public void init() {
        gp = new GamepadWrapper(gamepad1);
        left = hardwareMap.servo.get("leftServo");
        right = hardwareMap.servo.get("rightServo");
        leftpos = 0.5;
        rightpos = 0.5;
        if (left != null) {
            gp.y_button.addListener(new ButtonListener() {
                @Override
                public void onPressed() {
                    rightpos += .05;
                }

                @Override
                public void onReleased() {

                }
            });
            gp.a_button.addListener(new ButtonListener() {
                @Override
                public void onPressed() {
                    rightpos -= .05;
                }

                @Override
                public void onReleased() {

                }
            });
        }
        if (right != null) {
            gp.dp_up.addListener(new ButtonListener() {
                @Override
                public void onPressed() {
                    leftpos += .05;
                }

                @Override
                public void onReleased() {

                }
            });
            gp.dp_down.addListener(new ButtonListener() {
                @Override
                public void onPressed() {
                    leftpos -= .05;
                }

                @Override
                public void onReleased() {

                }
            });
        }
    }

    @Override
    public void loop() {

        Scheduler.instance.loop();

        if (left != null) {
            left.setPosition(leftpos);
        }
        if (right != null) {
            right.setPosition(rightpos);
        }
        telemetry.addData("left", leftpos);
        telemetry.addData("right", rightpos);

    }
}
