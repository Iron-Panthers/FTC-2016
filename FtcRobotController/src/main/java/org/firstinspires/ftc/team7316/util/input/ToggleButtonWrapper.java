package org.firstinspires.ftc.team7316.util.input;

import org.firstinspires.ftc.team7316.util.Loopable;
import org.firstinspires.ftc.team7316.util.Scheduler;

/**
 * Created by andrew on 9/15/16.
 */
public class ToggleButtonWrapper extends ButtonWrapper implements Loopable {

    private boolean value = false;

    public ToggleButtonWrapper(GamepadButton gamepadInput, GamepadWrapper gpSource) {
        super(gamepadInput, gpSource);

        Scheduler.instance.addTask(this);
    }

    @Override
    public boolean isPressed() {
        return value;
    }

    @Override
    public void init() {

    }

    @Override
    public void loop() {
        //according to ftc forums don't need to debounce

        boolean currentValue = super.isPressed();
        if (currentValue && !lastValue) {
            value = !value;
        }
        lastValue = currentValue;
    }

    @Override
    public boolean shouldRemove() {
        return false;
    }

    @Override
    public void terminate() {

    }
}
