package org.firstinspires.ftc.team7316.util.input;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.team7316.util.Loopable;
import org.firstinspires.ftc.team7316.util.hardware.CapballWrapper;

/**
 * Created by Maxim on 1/31/2017.
 */

public class CapballDropper implements ButtonListener, Loopable {

    public static final double OPEN = 1.0, CLOSED = 0.5;
    private Servo servo;

    public CapballDropper(Servo servo) {
        this.servo = servo;
    }

    @Override
    public void onPressed() {
        servo.setPosition(CLOSED);
    }

    @Override
    public void onReleased() {

    }

    @Override
    public void init() {
        servo.setPosition(OPEN);
    }

    @Override
    public void loop() {

    }

    @Override
    public boolean shouldRemove() {
        return false;
    }

    @Override
    public void terminate() {

    }
}
