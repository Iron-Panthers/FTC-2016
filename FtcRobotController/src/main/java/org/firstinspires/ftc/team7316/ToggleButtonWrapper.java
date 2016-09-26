package org.firstinspires.ftc.team7316;

/**
 * Created by andrew on 9/15/16.
 */
public class ToggleButtonWrapper extends ButtonWrapper implements Loopable {

    private boolean value = false, lastValue = false;

    public ToggleButtonWrapper(GamepadButton gamepadInput, GamepadWrapper gpSource) {
        super(gamepadInput, gpSource);

        Scheduler.instance.addTask(this);
    }

    @Override
    public boolean state() {
        return value;
    }

    @Override
    public void loop() {
        //according to ftc forums don't need to debounce

        boolean currentValue = super.state();
        if (currentValue && !lastValue) {
            value = !value;
        }
        lastValue = currentValue;
    }

    @Override
    public boolean shouldRemove() {
        return false;
    }
}
