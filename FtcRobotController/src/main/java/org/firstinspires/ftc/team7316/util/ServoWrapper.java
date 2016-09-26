package org.firstinspires.ftc.team7316.util;

import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by andrew on 9/20/16.
 */
public class ServoWrapper implements Loopable {

    private Servo servo;
    private ButtonWrapper button;

    public ServoWrapper(Servo servo, ButtonWrapper button) {
        this.servo = servo;
        this.button = button;

        Scheduler.instance.addTask(this);
    }

    @Override
    public void init() {

    }

    @Override
    public void loop() {
        if (button.state()) {
            servo.setPosition(1.0);
        } else {
            servo.setPosition(0.0);
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
