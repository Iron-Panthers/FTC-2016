package org.firstinspires.ftc.team7316.util.input;

import android.util.Log;

import org.firstinspires.ftc.team7316.util.Loopable;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.hardware.Hardware;

/**
 * Created by andrew on 11/19/16.
 */
public class RunCommandOnPress implements Loopable {

    private Loopable command;
    private ButtonWrapper button;
    private boolean previousValue;

    public RunCommandOnPress(ButtonWrapper button, Loopable command) {
        this.command = command;
        this.button = button;
    }

    @Override
    public void init() {
        this.previousValue = false;
    }

    @Override
    public void loop() {
        Hardware.log("this val", button.isPressed());
        Hardware.log("prev val", previousValue);
        if (button.isPressed()) {
            if (!previousValue) {
                command.init();
            } else {
                command.loop();
            }
        } else {
            if (previousValue) {
                command.terminate();
            }
        }
        previousValue = button.isPressed();
    }

    @Override
    public boolean shouldRemove() {
        return false;
    }

    @Override
    public void terminate() {

    }

}
