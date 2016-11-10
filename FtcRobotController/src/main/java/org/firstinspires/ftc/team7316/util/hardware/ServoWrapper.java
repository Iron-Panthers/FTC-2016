package org.firstinspires.ftc.team7316.util.hardware;

import android.util.Log;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.team7316.util.Loopable;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.input.ButtonWrapper;

/**
 * Created by andrew on 9/20/16.
 */
public class ServoWrapper implements Loopable {

    private Servo servo;
    private ButtonWrapper button;
    private double on, off;

    public ServoWrapper(Servo servo, ButtonWrapper button, double on, double off) {
        this.servo = servo;
        this.button = button;
        this.on = on;
        this.off = off;
    }

    @Override
    public void init() {

    }

    @Override
    public void loop() {
        if (button.isPressed()) {
            servo.setPosition(on);
        } else {
            servo.setPosition(off);
        }
    }

    @Override
    public boolean shouldRemove() {
        return false;
    }

    @Override
    public void terminate() {

    }
}
