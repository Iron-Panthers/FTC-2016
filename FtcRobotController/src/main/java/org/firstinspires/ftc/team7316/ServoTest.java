package org.firstinspires.ftc.team7316;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.input.ButtonListener;
import org.firstinspires.ftc.team7316.util.input.GamepadWrapper;

/**
 * Created by Maxim on 10/18/2016.
 */
@TeleOp(name="servoangletest")
public class ServoTest extends OpMode {

    private static final double increments = 0.05;
    double leftpos, rightpos;
    Servo left, right;
    GamepadWrapper gp;

    @Override
    public void init() {
        gp = new GamepadWrapper(gamepad1);
        left = hardwareMap.servo.get("ls");
        right = hardwareMap.servo.get("rs");
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

    @Override
    public void loop() {

        Scheduler.instance.loop();

        left.setPosition(leftpos);
        right.setPosition(rightpos);
        telemetry.addData("left", leftpos);
        telemetry.addData("right", rightpos);

    }
}
