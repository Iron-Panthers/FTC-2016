package org.firstinspires.ftc.team7316.util.hardware;

import android.util.Log;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.team7316.util.Loopable;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.input.ButtonWrapper;

/**
 * Created by wayne on 9/20/16.
 */
public class ServoWrapper implements Loopable {

    private Servo servo;
    private ButtonWrapper button;
    private boolean prevState = false;
    private double on, off, defaultPos;

    public ServoWrapper(Servo servo, ButtonWrapper button, double on, double off) {
        this.servo = servo;
        this.button = button;
        this.on = on;
        this.off = off;
        this.defaultPos = this.off;
    }

    public ServoWrapper(Servo servo, ButtonWrapper button, double on, double off, double defaultPos) {
        this.servo = servo;
        this.button = button;
        this.on = on;
        this.off = off;
        this.defaultPos = defaultPos;
    }

    @Override
    public void init() {
        servo.setPosition(this.defaultPos);
    }

    @Override
    public void loop() {
        if (button.isPressed() != this.prevState) {
            if (button.isPressed()) {
                servo.setPosition(on);
            } else {
                servo.setPosition(off);
            }
        }

        this.prevState = button.isPressed();
    }

    @Override
    public boolean shouldRemove() {
        return false;
    }

    @Override
    public void terminate() {

    }
}
